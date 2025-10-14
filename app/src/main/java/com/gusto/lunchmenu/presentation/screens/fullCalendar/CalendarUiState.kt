package com.gusto.lunchmenu.presentation.screens.fullCalendar

import androidx.compose.runtime.Immutable
import com.gusto.lunchmenu.data.models.CalendarItem
import com.gusto.lunchmenu.presentation.models.FoodItem
import java.time.LocalDate

/**
 * Represents the UI state for the FullCalendarScreen.
 */
@Immutable
data class CalendarUiState(
	val isLoading: Boolean = true,
	val calendarItems: List<CalendarItem> = emptyList(),
	val selectedDate: LocalDate? = null,
	val viewingFoodItem: FoodItem? = null,
)
