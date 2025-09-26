package com.gusto.lunchmenu.data.models

import java.time.LocalDate
import java.time.YearMonth

sealed interface CalendarItem {

	/**
	 * A sticky header for a month (e.g., "September 2025")
	 *
	 * @property yearMonth the month and the year
	 */
	data class MonthHeader(val yearMonth: YearMonth) : CalendarItem

	/**
	 * A single row in the calendar
	 *
	 * @property days days in the week
	 */
	data class WeekRow(val days: List<Day>) : CalendarItem {

		fun contains(date: LocalDate): Boolean {
			return days.any {
				it.date == date
			}
		}
	}
}