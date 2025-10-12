package com.gusto.lunchmenu.data

import com.gusto.lunchmenu.R
import com.gusto.lunchmenu.data.models.Weekday
import com.gusto.lunchmenu.presentation.models.FoodItem
import kotlinx.coroutines.delay

class LunchMenuDataSource {

	/*
	 * attributions:
	 * <a href="https://www.flaticon.com/free-icons/waffle" title="waffle icons">Waffle icons created by Freepik - Flaticon</a>
	 * 
	 */

	private val lunchItems = listOf(
		FoodItem(
			week = 1,
			weekday = Weekday.MONDAY,
			name = "Chicken and waffles",
			imageResId = R.drawable.chicken,
			price = 14.99,
			ingredients = listOf("Chicken", "Flour", "Egg", "Maple Syrup", "Butter"),
			allergens = setOf("Gluten", "Egg", "Dairy"),
			dietaryCertifications = listOf("High-Protein")
		),
		FoodItem(
			week = 1,
			weekday = Weekday.TUESDAY,
			name = "Tacos",
			imageResId = R.drawable.chicken, // Replace with a taco image
			price = 12.50,
			ingredients = listOf("Tortilla", "Ground Beef", "Lettuce", "Tomato", "Cheese"),
			allergens = setOf("Gluten", "Dairy")
		),
		FoodItem(
			week = 1,
			weekday = Weekday.WEDNESDAY,
			name = "Vegan Curry",
			imageResId = R.drawable.chicken, // Replace with a curry image
			price = 13.00,
			ingredients = listOf("Coconut Milk", "Tofu", "Broccoli", "Peppers", "Rice"),
			allergens = setOf("Soy"),
			dietaryCertifications = listOf("Vegan", "Gluten-Free")
		),
		FoodItem(
			week = 1,
			weekday = Weekday.THURSDAY,
			name = "Pizza",
			imageResId = R.drawable.chicken, // Replace with a pizza image
			price = 15.50,
			ingredients = listOf("Dough", "Tomato Sauce", "Cheese", "Pepperoni"),
			allergens = setOf("Gluten", "Dairy")
		),
		FoodItem(
			week = 1,
			weekday = Weekday.FRIDAY,
			name = "Sushi",
			imageResId = R.drawable.chicken, // Replace with a sushi image
			price = 18.00,
			ingredients = listOf("Rice", "Nori", "Tuna", "Avocado"),
			allergens = setOf("Fish"),
			dietaryCertifications = listOf("Gluten-Free")
		),
		FoodItem(
			week = 2,
			weekday = Weekday.MONDAY,
			name = "Breakfast for lunch",
			imageResId = R.drawable.chicken, // Replace with a breakfast image
			price = 11.25,
			ingredients = listOf("Pancakes", "Sausage", "Scrambled Eggs", "Syrup"),
			allergens = setOf("Gluten", "Egg", "Dairy")
		),
		FoodItem(
			week = 2,
			weekday = Weekday.TUESDAY,
			name = "Hamburgers",
			imageResId = R.drawable.chicken, // Replace with a hamburger image
			price = 14.00,
			ingredients = listOf("Bun", "Beef Patty", "Lettuce", "Tomato", "Onion"),
			allergens = setOf("Gluten")
		),
		FoodItem(
			week = 2,
			weekday = Weekday.WEDNESDAY,
			name = "Spaghetti",
			imageResId = R.drawable.chicken, // Replace with a spaghetti image
			price = 13.75,
			ingredients = listOf("Pasta", "Tomato Sauce", "Meatballs", "Parmesan Cheese"),
			allergens = setOf("Gluten", "Dairy")
		),
		FoodItem(
			week = 2,
			weekday = Weekday.THURSDAY,
			name = "Salmon",
			imageResId = R.drawable.chicken, // Replace with a salmon image
			price = 19.50,
			ingredients = listOf("Salmon Fillet", "Asparagus", "Lemon", "Dill"),
			allergens = setOf("Fish"),
			dietaryCertifications = listOf("Gluten-Free", "High-Protein")
		),
		FoodItem(
			week = 2,
			weekday = Weekday.FRIDAY,
			name = "Sandwiches",
			imageResId = R.drawable.chicken, // Replace with a sandwich image
			price = 9.75,
			ingredients = listOf("Bread", "Turkey", "Lettuce", "Mayonnaise"),
			allergens = setOf("Gluten", "Egg"),
		),
	)

	suspend fun getLunchMenu(): Map<Int, List<FoodItem>> {
		delay(1_500)
		return lunchItems.groupBy { it.week }
	}
}
