package com.gusto.lunchmenu.data

import com.gusto.lunchmenu.R
import com.gusto.lunchmenu.data.models.Weekday
import com.gusto.lunchmenu.presentation.models.FoodItem
import kotlinx.coroutines.delay

class LunchMenuDataSource {

	/*
	 * attributions:
	 * <a href="https://www.flaticon.com/free-icons/waffle" title="waffle icons">Waffle icons created by Freepik - Flaticon</a>
	 * <a href="https://www.flaticon.com/free-icons/taco" title="taco icons">Taco icons created by vectorsmarket15 - Flaticon</a>
	 * <a href="https://www.flaticon.com/free-icons/curry" title="curry icons">Curry icons created by Freepik - Flaticon</a>
	 * <a href="https://www.flaticon.com/free-icons/pizza" title="pizza icons">Pizza icons created by Vectors Tank - Flaticon</a>
	 * <a href="https://www.flaticon.com/free-icons/sushi" title="sushi icons">Sushi icons created by Freepik - Flaticon</a>
	 * <a href="https://www.flaticon.com/free-icons/breakfast" title="breakfast icons">Breakfast icons created by Konkapp - Flaticon</a>
	 * <a href="https://www.flaticon.com/free-icons/hamburger" title="hamburger icons">Hamburger icons created by Freepik - Flaticon</a>
	 * <a href="https://www.flaticon.com/free-icons/pasta" title="pasta icons">Pasta icons created by Freepik - Flaticon</a>
	 * <a href="https://www.flaticon.com/free-icons/salmon" title="salmon icons">Salmon icons created by Ylivdesign - Flaticon</a>
	 * <a href="https://www.flaticon.com/free-icons/sandwich" title="sandwich icons">Sandwich icons created by Freepik - Flaticon</a>
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
			dietaryCertifications = listOf("High-Protein"),
		),
		FoodItem(
			week = 1,
			weekday = Weekday.TUESDAY,
			name = "Tacos",
			imageResId = R.drawable.taco,
			price = 12.50,
			ingredients = listOf("Tortilla", "Ground Beef", "Lettuce", "Tomato", "Cheese"),
			allergens = setOf("Gluten", "Dairy"),
		),
		FoodItem(
			week = 1,
			weekday = Weekday.WEDNESDAY,
			name = "Vegan Curry",
			imageResId = R.drawable.curry,
			price = 13.00,
			ingredients = listOf("Coconut Milk", "Tofu", "Broccoli", "Peppers", "Rice"),
			allergens = setOf("Soy"),
			dietaryCertifications = listOf("Vegan", "Gluten-Free"),
		),
		FoodItem(
			week = 1,
			weekday = Weekday.THURSDAY,
			name = "Pizza",
			imageResId = R.drawable.pizza,
			price = 15.50,
			ingredients = listOf("Dough", "Tomato Sauce", "Cheese", "Pepperoni"),
			allergens = setOf("Gluten", "Dairy"),
		),
		FoodItem(
			week = 1,
			weekday = Weekday.FRIDAY,
			name = "Sushi",
			imageResId = R.drawable.sushi,
			price = 18.00,
			ingredients = listOf("Rice", "Nori", "Tuna", "Avocado"),
			allergens = setOf("Fish"),
			dietaryCertifications = listOf("Gluten-Free"),
		),
		FoodItem(
			week = 2,
			weekday = Weekday.MONDAY,
			name = "Breakfast for lunch",
			imageResId = R.drawable.breakfast,
			price = 11.25,
			ingredients = listOf("Pancakes", "Sausage", "Scrambled Eggs", "Syrup"),
			allergens = setOf("Gluten", "Egg", "Dairy"),
		),
		FoodItem(
			week = 2,
			weekday = Weekday.TUESDAY,
			name = "Hamburgers",
			imageResId = R.drawable.hamburger,
			price = 14.00,
			ingredients = listOf("Bun", "Beef Patty", "Lettuce", "Tomato", "Onion"),
			allergens = setOf("Gluten"),
		),
		FoodItem(
			week = 2,
			weekday = Weekday.WEDNESDAY,
			name = "Spaghetti",
			imageResId = R.drawable.spaghetti,
			price = 13.75,
			ingredients = listOf("Pasta", "Tomato Sauce", "Meatballs", "Parmesan Cheese"),
			allergens = setOf("Gluten", "Dairy"),
		),
		FoodItem(
			week = 2,
			weekday = Weekday.THURSDAY,
			name = "Salmon",
			imageResId = R.drawable.salmon,
			price = 19.50,
			ingredients = listOf("Salmon Fillet", "Asparagus", "Lemon", "Dill"),
			allergens = setOf("Fish"),
			dietaryCertifications = listOf("Gluten-Free", "High-Protein"),
		),
		FoodItem(
			week = 2,
			weekday = Weekday.FRIDAY,
			name = "Sandwiches",
			imageResId = R.drawable.sandwich,
			price = 9.75,
			ingredients = listOf("Bread", "Turkey", "Lettuce", "Mayonnaise"),
			allergens = setOf("Gluten", "Egg"),
		),
	)

	suspend fun getLunchMenu(): Map<Int, List<FoodItem>> {
		delay(1_500)
		return lunchItems.groupBy {
			it.week
		}
	}
}
