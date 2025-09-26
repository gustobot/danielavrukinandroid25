package com.gusto.lunchmenu.presentation.components

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gusto.lunchmenu.R
import com.gusto.lunchmenu.presentation.models.FoodItem
import com.gusto.lunchmenu.data.models.Weekday
import com.gusto.lunchmenu.ui.theme.MyApplicationTheme

@Composable
fun FoodItemTileGridView(
	items: List<FoodItem>,
	modifier: Modifier = Modifier,
) {
	LazyHorizontalGrid(
		rows = GridCells.Fixed(items.size),
		modifier = modifier,
		content = {
			items.forEach { item ->
				item {
					FoodItemTileView(
						foodItem = item,
						isSelected = false,
						isEnabled = true,
						onItemClick = {},
					)
				}
			}
		},
	)
}

@Preview
@Composable
fun FoodItemTileGridViewPreview() {
	MyApplicationTheme {
		FoodItemTileGridView(
			items = listOf(
				FoodItem(
					week = 1,
					weekday = Weekday.MONDAY,
					name = "Chicken and waffles",
					imageResId = R.drawable.chicken,
				),
				FoodItem(
					week = 1,
					weekday = Weekday.TUESDAY,
					name = "Chicken and waffles",
					imageResId = R.drawable.chicken,
				),
				FoodItem(
					week = 1,
					weekday = Weekday.WEDNESDAY,
					name = "Chicken and waffles",
					imageResId = R.drawable.chicken,
				),
			),
		)
	}
}