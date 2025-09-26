package com.gusto.lunchmenu.data

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable

// week is 1-indexed
@Immutable
data class FoodItem(
	val week: Int,
	val weekday: Weekday,
	val name: String,
	@get:DrawableRes val imageResId: Int,
)
