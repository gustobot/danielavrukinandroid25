package com.gusto.lunchmenu.data.models

import com.gusto.lunchmenu.presentation.models.FoodItem
import java.time.LocalDate

/**
 * A single day in the calendar
 *
 * @property date the date of the day
 * @property foodItem null if weekend or no item available
 */
data class Day(
	val date: LocalDate,
	val foodItem: FoodItem?,
)
