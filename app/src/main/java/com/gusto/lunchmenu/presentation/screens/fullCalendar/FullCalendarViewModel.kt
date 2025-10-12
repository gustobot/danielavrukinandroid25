package com.gusto.lunchmenu.presentation.screens.fullCalendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gusto.lunchmenu.data.MenuRepository
import com.gusto.lunchmenu.data.models.CalendarItem
import com.gusto.lunchmenu.data.models.Day
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.TemporalAdjusters

class FullCalendarViewModel(
	private val menuRepository: MenuRepository = MenuRepository(),
) : ViewModel() {

	private val _uiState = MutableStateFlow(CalendarUiState())
	val uiState: StateFlow<CalendarUiState> = _uiState.asStateFlow()

	init {
		loadCalendarData()
	}

	/**
	 * Handles the user selecting a date on the calendar.
	 * This now also sets the item to be viewed in the bottom sheet.
	 */
	fun onDateSelected(date: LocalDate) {
		// Get the complete FoodItem directly from the repository
		val foodItem = menuRepository.getFoodItemForDate(date)
		_uiState.update { currentState ->
			currentState.copy(
				selectedDate = date,
				viewingFoodItem = foodItem // Set the item to be viewed
			)
		}
	}

	/**
	 * Called when the bottom sheet is dismissed.
	 */
	fun onDismissBottomSheet() {
		_uiState.update { currentState ->
			currentState.copy(
				selectedDate = null, // Also clear selection
				viewingFoodItem = null
			)
		}
	}

	private fun loadCalendarData() {
		viewModelScope.launch {
			_uiState.update { it.copy(isLoading = true) }
			menuRepository.loadMenu()
			val today = LocalDate.now()
			val items = generateCalendarItems(
				start = today.minusMonths(6),
				end = today.plusMonths(6),
				menuRepository = menuRepository
			)
			_uiState.update {
				it.copy(
					isLoading = false,
					calendarItems = items
				)
			}
		}
	}

	// This helper function can be moved from the Composable into the ViewModel
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

			if (YearMonth.from(currentDay) != currentMonth && currentDay.isBefore(lastDay)) {
				currentMonth = YearMonth.from(currentDay)
				items.add(CalendarItem.MonthHeader(currentMonth))
			}
		}
		return items
	}
}