package com.gusto.lunchmenu.presentation.sheets.foodItemDetails.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gusto.lunchmenu.ui.util.ThemedPreview

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FoodItemIngredients(
	ingredients: List<String>,
	allergens: Set<String>,
	modifier: Modifier = Modifier,
) {
	if (ingredients.isNotEmpty()) {
		Column(modifier = modifier) {
			Text(
				text = "Ingredients",
				style = MaterialTheme.typography.titleMedium,
				fontWeight = FontWeight.Bold,
			)

			Spacer(Modifier.height(8.dp))

			FlowRow(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.spacedBy(8.dp),
				verticalArrangement = Arrangement.spacedBy(8.dp),
			) {
				ingredients.forEach { ingredient ->
					// The chip is highlighted if the ingredient name is present in the allergen set.
					val isAllergen = ingredient in allergens
					IngredientChip(text = ingredient, isAllergen = isAllergen)
				}
			}
		}
	}
}

@Preview(name = "Default")
@Composable
private fun FoodItemIngredientsPreview_Default() {
	ThemedPreview {
		FoodItemIngredients(
			ingredients = listOf("Chicken", "Flour", "Egg", "Maple Syrup", "Butter"),
			allergens = setOf("Egg", "Butter"), // Highlight "Egg" and "Butter"
			modifier = Modifier.padding(16.dp),
		)
	}
}

@Preview(name = "No Allergens")
@Composable
private fun FoodItemIngredientsPreview_NoAllergens() {
	ThemedPreview {
		FoodItemIngredients(
			ingredients = listOf("Rice", "Nori", "Tuna", "Avocado"),
			allergens = emptySet(),
			modifier = Modifier.padding(16.dp),
		)
	}
}

@Preview(name = "Long List")
@Composable
private fun FoodItemIngredientsPreview_LongList() {
	ThemedPreview {
		FoodItemIngredients(
			ingredients = listOf(
				"Pasta", "Tomato Sauce", "Meatballs", "Parmesan Cheese",
				"Olive Oil", "Garlic", "Onion", "Basil", "Oregano", "Salt", "Pepper",
			),
			allergens = setOf("Pasta", "Parmesan Cheese"),
			modifier = Modifier.padding(16.dp),
		)
	}
}

@Preview(name = "Empty List")
@Composable
private fun FoodItemIngredientsPreview_Empty() {
	ThemedPreview {
		FoodItemIngredients(
			ingredients = emptyList(),
			allergens = emptySet(),
			modifier = Modifier.padding(16.dp),
		)
	}
}

@Preview(name = "Tablet", device = Devices.PIXEL_TABLET)
@Composable
private fun FoodItemIngredientsPreview_Tablet() {
	ThemedPreview {
		FoodItemIngredients(
			ingredients = listOf(
				"Pancakes", "Sausage", "Scrambled Eggs", "Syrup",
				"Flour", "Milk", "Butter", "Salt", "Pepper",
			),
			allergens = setOf("Pancakes", "Scrambled Eggs", "Milk", "Butter"),
			modifier = Modifier.padding(16.dp),
		)
	}
}
