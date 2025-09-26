package com.gusto.lunchmenu.data.models

import java.time.DayOfWeek

enum class Weekday {
	MONDAY,
	TUESDAY,
	WEDNESDAY,
	THURSDAY,
	FRIDAY;

	companion object {
		/**
		 * Converts a java.time.DayOfWeek to our domain-specific Weekday.
		 * Returns null for Saturday and Sunday.
		 */
		fun from(dayOfWeek: DayOfWeek): Weekday? {
			return when (dayOfWeek) {
				DayOfWeek.MONDAY -> MONDAY
				DayOfWeek.TUESDAY -> TUESDAY
				DayOfWeek.WEDNESDAY -> WEDNESDAY
				DayOfWeek.THURSDAY -> THURSDAY
				DayOfWeek.FRIDAY -> FRIDAY
				else -> null
			}
		}
	}
}
