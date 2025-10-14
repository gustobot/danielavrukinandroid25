package com.gusto.lunchmenu.data.models

import androidx.compose.runtime.Immutable
import java.time.LocalDate
import java.time.YearMonth

@Immutable
sealed interface CalendarItem {

	/**
	 * A sticky header for a month (e.g., "September 2025")
	 *
	 * @property yearMonth the month and the year
	 */
	@Immutable
	data class MonthHeader(val yearMonth: YearMonth) : CalendarItem

	/**
	 * A single row in the calendar
	 *
	 * @property days days in the week
	 */
	@Immutable
	data class WeekRow(val days: List<Day>) : CalendarItem {

		fun contains(date: LocalDate): Boolean {
			return days.any {
				it.date == date
			}
		}
	}
}
