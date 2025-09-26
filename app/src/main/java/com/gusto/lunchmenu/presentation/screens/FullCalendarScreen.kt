package com.gusto.lunchmenu.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gusto.lunchmenu.data.MenuRepository
import com.gusto.lunchmenu.data.models.CalendarItem
import com.gusto.lunchmenu.data.models.Day
import com.gusto.lunchmenu.presentation.components.FoodItemTileView
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FullCalendarScreen(
	// In a real app, inject this via a ViewModel
	menuRepository: MenuRepository = remember { MenuRepository() },
	modifier: Modifier = Modifier,
) {
	val coroutineScope = rememberCoroutineScope()
	val lazyListState = rememberLazyListState()

	// State for the calendar data and loading status
	var calendarItems by remember { mutableStateOf<List<CalendarItem>>(emptyList()) }
	var isLoading by remember { mutableStateOf(true) }

	// State for selection and the date picker
	var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
	val today = remember { LocalDate.now() }
	val showDatePicker = remember { mutableStateOf(false) }

	// Load menu data and generate the calendar structure
	LaunchedEffect(Unit) {
		isLoading = true
		menuRepository.loadMenu()
		calendarItems = generateCalendarItems(
			start = today.minusMonths(6),
			end = today.plusMonths(6),
			menuRepository = menuRepository
		)
		isLoading = false

		// Auto-scroll to today's date on initial load
		val todayIndex = findWeekIndexForDate(calendarItems, today)
		if (todayIndex != -1) {
			lazyListState.scrollToItem(todayIndex)
		}
	}

	Scaffold(
		modifier = modifier,
		topBar = {
			TopAppBar(
				title = { Text("Lunch Calendar") },
				actions = {
					// Button to open the date picker
					Button(onClick = { showDatePicker.value = true }) {
						Text("Pick Date")
					}
					Spacer(Modifier.width(8.dp))
					// Button to scroll back to today
					Button(onClick = {
						coroutineScope.launch {
							val todayIndex = findWeekIndexForDate(calendarItems, today)
							if (todayIndex != -1) {
								lazyListState.animateScrollToItem(todayIndex)
							}
						}
					}) {
						Text("Today")
					}
				}
			)
		}
	) { paddingValues ->
		if (isLoading) {
			Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
				CircularProgressIndicator()
			}
		} else {
			LazyColumn(
				modifier = Modifier.padding(paddingValues),
				state = lazyListState,
				contentPadding = PaddingValues(horizontal = 16.dp)
			) {
				calendarItems.forEach { item ->
					when (item) {
						is CalendarItem.MonthHeader -> {
							// Sticky header for the month
							stickyHeader {
								MonthHeader(item.yearMonth)
							}
						}

						is CalendarItem.WeekRow -> {
							item {
								// Row containing the FoodItemTileViews for the week
								WeekView(
									week = item,
									today = today,
									selectedDate = selectedDate,
									onDateSelected = { date ->
										selectedDate = if (selectedDate == date) null else date
									}
								)
							}
						}
					}
				}
			}
		}
	}

	// Material 3 Date Picker Dialog
	if (showDatePicker.value) {
		val datePickerState = rememberDatePickerState(
			initialSelectedDateMillis = System.currentTimeMillis()
		)
		DatePickerDialog(
			onDismissRequest = { showDatePicker.value = false },
			confirmButton = {
				TextButton(
					onClick = {
						showDatePicker.value = false
						val millis = datePickerState.selectedDateMillis ?: return@TextButton
						val newDate = LocalDate.ofEpochDay(millis / (1000 * 60 * 60 * 24))

						// Scroll to the selected date
						coroutineScope.launch {
							val index = findWeekIndexForDate(calendarItems, newDate)
							if (index != -1) {
								lazyListState.animateScrollToItem(index)
								selectedDate = newDate // Also select the date
							}
						}
					}
				) { Text("OK") }
			},
			dismissButton = {
				TextButton(onClick = { showDatePicker.value = false }) { Text("Cancel") }
			}
		) {
			DatePicker(state = datePickerState)
		}
	}
}

@Composable
private fun MonthHeader(yearMonth: YearMonth) {
	val formatter = remember { DateTimeFormatter.ofPattern("MMMM yyyy") }
	Text(
		text = yearMonth.format(formatter),
		fontSize = 20.sp,
		fontWeight = FontWeight.Bold,
		modifier = Modifier
			.fillMaxWidth()
			.background(MaterialTheme.colorScheme.surface)
			.padding(vertical = 8.dp)
	)
}

@Composable
private fun WeekView(
	week: CalendarItem.WeekRow,
	today: LocalDate,
	selectedDate: LocalDate?,
	onDateSelected: (LocalDate) -> Unit,
) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(vertical = 8.dp),
		horizontalArrangement = Arrangement.spacedBy(8.dp)
	) {
		// Create a map of the days in the week for easy lookup
		val daysByWeekDay = week.days.associateBy { it.date.dayOfWeek }

		// Define the work week explicitly
		val workWeek = listOf(
			DayOfWeek.MONDAY,
			DayOfWeek.TUESDAY,
			DayOfWeek.WEDNESDAY,
			DayOfWeek.THURSDAY,
			DayOfWeek.FRIDAY
		)

		// Iterate through the explicit list to ensure a consistent 5-day layout
		for (dayOfWeek in workWeek) {
			val day = daysByWeekDay[dayOfWeek]
			Box(modifier = Modifier.weight(1f)) {
				if (day != null && day.foodItem != null) {
					FoodItemTileView(
						foodItem = day.foodItem,
						date = day.date, // Pass the date to the tile
						isSelected = day.date == selectedDate,
						onItemClick = { onDateSelected(day.date) },
						isEnabled = !day.date.isBefore(today)
					)
				}
			}
		}
	}
}

// Helper function to generate the list of CalendarItems
private fun generateCalendarItems(
	start: LocalDate,
	end: LocalDate,
	menuRepository: MenuRepository,
): List<CalendarItem> {
	val items = mutableListOf<CalendarItem>()
	var currentMonth = YearMonth.from(start)
	items.add(CalendarItem.MonthHeader(currentMonth))

	val firstDay = start.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
	val lastDay = end.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY))

	var currentDay = firstDay
	while (currentDay.isBefore(lastDay)) {
		val weekDates = (0..4).map { currentDay.plusDays(it.toLong()) }
		val weekDays = weekDates.map { date ->
			Day(date, menuRepository.getFoodItemForDate(date))
		}
		items.add(CalendarItem.WeekRow(weekDays))

		currentDay = currentDay.plusWeeks(1)

		// Add a new month header if the month has changed
		if (YearMonth.from(currentDay) != currentMonth) {
			currentMonth = YearMonth.from(currentDay)
			items.add(CalendarItem.MonthHeader(currentMonth))
		}
	}
	return items
}

// Helper to find the index of the week containing a specific date
private fun findWeekIndexForDate(items: List<CalendarItem>, date: LocalDate): Int {
	return items.indexOfFirst { it is CalendarItem.WeekRow && it.contains(date) }
}