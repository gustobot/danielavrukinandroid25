package com.gusto.lunchmenu.presentation.components

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gusto.lunchmenu.R
import com.gusto.lunchmenu.data.FoodItem
import com.gusto.lunchmenu.data.Weekday
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
						item = item,
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
					weekday = Weekday.MONDAY,
					name = "Chicken and waffles",
					imageResId = R.drawable.chicken,
				),
				FoodItem(
					week = 1,
					weekday = Weekday.MONDAY,
					name = "Chicken and waffles",
					imageResId = R.drawable.chicken,
				),
			),
		)
	}
}