package com.gusto.lunchmenu.data

import com.gusto.lunchmenu.R
import com.gusto.lunchmenu.presentation.models.FoodItem
import com.gusto.lunchmenu.data.models.Weekday
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
		),
		FoodItem(
			week = 1,
			weekday = Weekday.TUESDAY,
			name = "Tacos",
			imageResId = R.drawable.chicken,
		),
		FoodItem(
			week = 1,
			weekday = Weekday.WEDNESDAY,
			name = "Curry",
			imageResId = R.drawable.chicken,
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