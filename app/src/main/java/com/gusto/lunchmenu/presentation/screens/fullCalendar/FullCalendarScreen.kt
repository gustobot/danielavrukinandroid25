package com.gusto.lunchmenu.presentation.screens.fullCalendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gusto.lunchmenu.data.MenuRepository
import com.gusto.lunchmenu.data.models.CalendarItem
import com.gusto.lunchmenu.data.models.Day
import com.gusto.lunchmenu.presentation.components.FoodItemDetailsSheet
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
	modifier: Modifier = Modifier,
	viewModel: FullCalendarViewModel = viewModel(), // Obtain ViewModel instance
) {
	val uiState by viewModel.uiState.collectAsState() // Observe UI state
	val coroutineScope = rememberCoroutineScope()
	val lazyListState = rememberLazyListState()
	val sheetState = rememberModalBottomSheetState()

	// State for the date picker dialog can remain in the composable
	var showDatePicker by rememberSaveable { mutableStateOf(false) }
	val today = remember { LocalDate.now() }

	// This effect now reacts to changes in the calendarItems list
	LaunchedEffect(uiState.calendarItems) {
		// Auto-scroll to today's date only when the list is first loaded
		if (lazyListState.firstVisibleItemIndex == 0 && lazyListState.firstVisibleItemScrollOffset == 0) {
			val todayIndex = findWeekIndexForDate(uiState.calendarItems, today)
			if (todayIndex != -1) {
				lazyListState.scrollToItem(todayIndex)
			}
		}
	}

	// Show the bottom sheet when there is a food item being viewed
	if (uiState.viewingFoodItem != null) {
		ModalBottomSheet(
			onDismissRequest = { viewModel.onDismissBottomSheet() },
			sheetState = sheetState
		) {
			// Content of the bottom sheet
			FoodItemDetailsSheet(foodItem = uiState.viewingFoodItem!!)
		}
	}

	Scaffold(
		modifier = modifier,
		topBar = {
			TopAppBar(
				title = { Text("Lunch Calendar") },
				actions = {
					// Button to open the date picker
					Button(onClick = { showDatePicker = true }) {
						Text("Pick Date")
					}
					Spacer(Modifier.width(8.dp))
					// Button to scroll back to today
					Button(onClick = {
						coroutineScope.launch {
							val todayIndex = findWeekIndexForDate(uiState.calendarItems, today)
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
		if (uiState.isLoading) {
			Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
				CircularProgressIndicator()
			}
		} else {
			LazyColumn(
				modifier = Modifier.padding(paddingValues),
				state = lazyListState,
				contentPadding = PaddingValues(horizontal = 16.dp)
			) {
				// The list is now driven by the state from the ViewModel
				uiState.calendarItems.forEach { item ->
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
									selectedDate = uiState.selectedDate, // Use selectedDate from state
									onDateSelected = viewModel::onDateSelected // Pass event to ViewModel
								)
							}
						}
					}
				}
			}
		}
	}

	// Material 3 Date Picker Dialog
	if (showDatePicker) {
		val datePickerState = rememberDatePickerState(
			initialSelectedDateMillis = System.currentTimeMillis()
		)
		DatePickerDialog(
			onDismissRequest = { showDatePicker = false },
			confirmButton = {
				TextButton(
					onClick = {
						showDatePicker = false
						val millis = datePickerState.selectedDateMillis ?: return@TextButton
						val newDate = LocalDate.ofEpochDay(millis / (1000 * 60 * 60 * 24))

						// Scroll to the selected date
						coroutineScope.launch {
							val index = findWeekIndexForDate(uiState.calendarItems, newDate)
							if (index != -1) {
								lazyListState.animateScrollToItem(index)
								viewModel.onDateSelected(newDate) // Update selection in ViewModel
							}
						}
					}
				) { Text("OK") }
			},
			dismissButton = {
				TextButton(onClick = { showDatePicker = false }) { Text("Cancel") }
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

	// Use LazyRow for a horizontally scrollable container
	LazyRow(
		modifier = Modifier
			.fillMaxWidth()
			.padding(vertical = 8.dp),
		horizontalArrangement = Arrangement.spacedBy(8.dp)
	) {
		// Iterate through the explicit list to ensure a consistent 5-day layout
		items(workWeek) { dayOfWeek ->
			val day = daysByWeekDay[dayOfWeek]

			if (day != null && day.foodItem != null) {
				FoodItemTileView(
					modifier = Modifier
						.width(160.dp) // Set a fixed width for each tile
						.height(280.dp), // Set a fixed height for each tile
					foodItem = day.foodItem,
					date = day.date,
					isSelected = day.date == selectedDate,
					onItemClick = { onDateSelected(day.date) },
					isEnabled = !day.date.isBefore(today)
				)
			} else {
				// Add a spacer for days that don't have a menu item (e.g., out of range)
				// This keeps the alignment consistent if a week is partially filled.
				Spacer(modifier = Modifier.width(160.dp))
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
