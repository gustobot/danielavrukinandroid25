package com.gusto.lunchmenu.presentation.sheets.foodItemDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gusto.lunchmenu.data.models.Weekday
import com.gusto.lunchmenu.presentation.models.FoodItem
import com.gusto.lunchmenu.presentation.sheets.foodItemDetails.components.CertificationRow
import com.gusto.lunchmenu.presentation.sheets.foodItemDetails.components.IngredientChip
import com.gusto.lunchmenu.ui.util.ThemedPreview

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FoodItemDetailsSheet(foodItem: FoodItem, modifier: Modifier = Modifier) {
	Column(
		modifier = modifier
			.fillMaxWidth()
			.padding(horizontal = 24.dp, vertical = 16.dp)
	) {
		// Header: Name and Price
		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically
		) {
			Text(
				text = foodItem.name,
				style = MaterialTheme.typography.headlineMedium,
				fontWeight = FontWeight.Bold
			)
			Text(
				text = "$%.2f".format(foodItem.price),
				style = MaterialTheme.typography.headlineMedium,
				color = MaterialTheme.colorScheme.primary
			)
		}

		HorizontalDivider(
			modifier = Modifier.padding(vertical = 16.dp),
			thickness = DividerDefaults.Thickness,
			color = DividerDefaults.color,
		)

		// Ingredients Section
		Text(
			text = "Ingredients",
			style = MaterialTheme.typography.titleMedium,
			fontWeight = FontWeight.Bold
		)
		Spacer(Modifier.height(8.dp))
		FlowRow(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.spacedBy(8.dp),
			verticalArrangement = Arrangement.spacedBy(8.dp)
		) {
			foodItem.ingredients.forEach { ingredient ->
				val isAllergen = ingredient in foodItem.allergens
				IngredientChip(text = ingredient, isAllergen = isAllergen)
			}
		}

		Spacer(Modifier.height(24.dp))

		// Dietary Certifications Section
		if (foodItem.dietaryCertifications.isNotEmpty()) {
			Text(
				text = "Dietary Information",
				style = MaterialTheme.typography.titleMedium,
				fontWeight = FontWeight.Bold
			)
			Spacer(Modifier.height(8.dp))
			foodItem.dietaryCertifications.forEach { certification ->
				CertificationRow(text = certification)
			}
		}
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
				dietaryCertifications = listOf("High-Protein", "Contains Nuts")
			)
		)
	}
}