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
			allergens = setOf("Gluten", "Dairy"),
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
			imageResId = R.drawable.chicken,
		),
		FoodItem(
			week = 1,
			weekday = Weekday.FRIDAY,
			name = "Sushi",
			imageResId = R.drawable.chicken,
		),
		FoodItem(
			week = 2,
			weekday = Weekday.MONDAY,
			name = "Breakfast for lunch",
			imageResId = R.drawable.chicken,
		),
		FoodItem(
			week = 2,
			weekday = Weekday.TUESDAY,
			name = "Hamburgers",
			imageResId = R.drawable.chicken,
		),
		FoodItem(
			week = 2,
			weekday = Weekday.WEDNESDAY,
			name = "Spaghetti",
			imageResId = R.drawable.chicken,
		),
		FoodItem(
			week = 2,
			weekday = Weekday.THURSDAY,
			name = "Salmon",
			imageResId = R.drawable.chicken,
		),
		FoodItem(
			week = 2,
			weekday = Weekday.FRIDAY,
			name = "Sandwiches",
			imageResId = R.drawable.chicken,
			price = 9.75,
			ingredients = listOf("Bread", "Turkey", "Lettuce", "Mayonnaise"),
			allergens = setOf("Gluten", "Egg"),
		),
	)

    suspend fun getLunchMenu(): Map<Int, List<FoodItem>> {
        delay(3_000)
	    return lunchItems
		    .groupBy {
				it.week
		    }
        /*return mapOf(
            1 to listOf("Chicken and waffles", "Tacos", "Curry", "Pizza", "Sushi"),
            2 to listOf("Breakfast for lunch", "Hamburgers", "Spaghetti", "Salmon", "Sandwiches"),
        )*/
    }
}