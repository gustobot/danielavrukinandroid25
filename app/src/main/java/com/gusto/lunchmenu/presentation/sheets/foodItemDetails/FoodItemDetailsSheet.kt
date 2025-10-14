package com.gusto.lunchmenu.presentation.sheets.foodItemDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gusto.lunchmenu.data.models.Weekday
import com.gusto.lunchmenu.presentation.models.FoodItem
import com.gusto.lunchmenu.presentation.sheets.foodItemDetails.components.FoodItemCertifications
import com.gusto.lunchmenu.presentation.sheets.foodItemDetails.components.FoodItemHeader
import com.gusto.lunchmenu.presentation.sheets.foodItemDetails.components.FoodItemIngredients
import com.gusto.lunchmenu.ui.util.ThemedPreview

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FoodItemDetailsSheet(foodItem: FoodItem, modifier: Modifier = Modifier) {
	Column(
		modifier = modifier
			.fillMaxWidth()
			.padding(horizontal = 24.dp, vertical = 16.dp),
	) {
		FoodItemHeader(
			foodItemName = foodItem.name,
			foodItemPrice = foodItem.price,
		)

		HorizontalDivider(
			modifier = Modifier.padding(vertical = 16.dp),
			thickness = DividerDefaults.Thickness,
			color = DividerDefaults.color,
		)

		FoodItemIngredients(
			ingredients = foodItem.ingredients,
			allergens = foodItem.allergens,
		)

		Spacer(Modifier.height(24.dp))

		FoodItemCertifications(
			certifications = foodItem.dietaryCertifications,
		)
	}
}

@Preview
@Composable
private fun FoodItemDetailsSheetPreview() {
	ThemedPreview {
		FoodItemDetailsSheet(
			foodItem = FoodItem(
				week = 1,
				weekday = Weekday.MONDAY,
				name = "Chicken and waffles",
				imageResId = 0,
				price = 14.99,
				ingredients = listOf("Chicken", "Flour", "Egg", "Maple Syrup", "Butter"),
				allergens = setOf("Gluten", "Egg", "Dairy"),
				dietaryCertifications = listOf("High-Protein", "Contains Nuts"),
			),
		)
	}
}
