package com.gusto.lunchmenu.presentation.models

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import com.gusto.lunchmenu.data.models.Weekday

// week is 1-indexed
@Immutable
data class FoodItem(
	val week: Int,
	val weekday: Weekday,
	val name: String,
	@get:DrawableRes val imageResId: Int,
	// Add new properties for the bottom sheet
	val price: Double = 0.0,
	val ingredients: List<String> = emptyList(),
	val allergens: Set<String> = emptySet(),
	val dietaryCertifications: List<String> = emptyList(),
)
