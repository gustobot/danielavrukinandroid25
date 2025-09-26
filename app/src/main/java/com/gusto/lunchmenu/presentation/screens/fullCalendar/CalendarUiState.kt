package com.gusto.lunchmenu.presentation.screens.fullCalendar

import com.gusto.lunchmenu.data.models.CalendarItem
import java.time.LocalDate

/**
 * Represents the UI state for the FullCalendarScreen.
 */
data class CalendarUiState(
	val isLoading: Boolean = true,
	val calendarItems: List<CalendarItem> = emptyList(),
	val selectedDate: LocalDate? = null,
)