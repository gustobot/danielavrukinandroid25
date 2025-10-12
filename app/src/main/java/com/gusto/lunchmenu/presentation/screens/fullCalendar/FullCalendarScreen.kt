package com.gusto.lunchmenu.presentation.screens.fullCalendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gusto.lunchmenu.data.models.CalendarItem
import com.gusto.lunchmenu.presentation.screens.fullCalendar.components.MonthHeader
import com.gusto.lunchmenu.presentation.screens.fullCalendar.components.WeekView
import com.gusto.lunchmenu.presentation.sheets.foodItemDetails.FoodItemDetailsSheet
import java.time.DayOfWeek
import java.time.LocalDate

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FullCalendarScreen(
	modifier: Modifier = Modifier,
	viewModel: FullCalendarViewModel = viewModel(), // Obtain ViewModel instance
) {
	val uiState by viewModel.uiState.collectAsState() // Observe UI state
	val verticalLazyListState = rememberLazyListState()
	val horizontalLazyListStates = remember(uiState.calendarItems) {
		val weekRows = uiState.calendarItems.filterIsInstance<CalendarItem.WeekRow>()
		weekRows.associate { week -> week.hashCode() to LazyListState() }
	}

	// State for the date picker dialog can remain in the composable
	var showDatePicker by rememberSaveable { mutableStateOf(false) }
	var initialScrollDone by rememberSaveable { mutableStateOf(false) }
	val today = remember { LocalDate.now() }

	// Effect to handle all scrolling logic
	LaunchedEffect(uiState.selectedDate, uiState.calendarItems) {
		val dateToScrollTo = uiState.selectedDate ?: if (!initialScrollDone) today else null

		if (dateToScrollTo != null && uiState.calendarItems.isNotEmpty()) {
			val targetDate = when (dateToScrollTo.dayOfWeek) {
				DayOfWeek.SATURDAY -> dateToScrollTo.plusDays(2)
				DayOfWeek.SUNDAY -> dateToScrollTo.plusDays(1)
				else -> dateToScrollTo
			}

			val weekIndex = findWeekIndexForDate(uiState.calendarItems, targetDate)
			if (weekIndex != -1) {
				// Vertical scroll
				verticalLazyListState.animateScrollToItem(weekIndex)

				// Horizontal scroll
				val weekItem = uiState.calendarItems[weekIndex] as? CalendarItem.WeekRow
				if (weekItem != null) {
					val dayIndex = weekItem.days.indexOfFirst { it.date == targetDate }
					if (dayIndex != -1) {
						horizontalLazyListStates[weekItem.hashCode()]?.animateScrollToItem(dayIndex)
					}
				}
			}
		}
	}


	// Show the bottom sheet when there is a food item being viewed
	if (uiState.viewingFoodItem != null) {
		ModalBottomSheet(
			onDismissRequest = { viewModel.onDismissBottomSheet() },
			sheetState = rememberModalBottomSheetState()
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
					Button(onClick = { viewModel.onDateSelected(today) }) {
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
				state = verticalLazyListState,
				contentPadding = PaddingValues(horizontal = 16.dp)
			) {
				uiState.calendarItems.forEach { item ->
					when (item) {
						is CalendarItem.MonthHeader -> {
							stickyHeader(
								key = item.yearMonth,
								contentType = "MonthHeader"
							) {
								MonthHeader(yearMonth = item.yearMonth)
							}
						}

						is CalendarItem.WeekRow -> {
							val weekKey = item.days.firstOrNull()?.date
							item(
								key = weekKey,
								contentType = "WeekRow"
							) {
								WeekView(
									week = item,
									today = today,
									selectedDate = uiState.selectedDate,
									onDateSelected = viewModel::onDateSelected,
									lazyListState = horizontalLazyListStates[item.hashCode()]
										?: rememberLazyListState()
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
						viewModel.onDateSelected(newDate)
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

// Helper to find the index of the week containing a specific date
private fun findWeekIndexForDate(items: List<CalendarItem>, date: LocalDate): Int {
	// Adjust the date to the Monday of its week to handle weekends and ensure we find a valid week.
	val searchDate = date.with(DayOfWeek.MONDAY)
	return items.indexOfFirst { it is CalendarItem.WeekRow && it.contains(searchDate) }
}
