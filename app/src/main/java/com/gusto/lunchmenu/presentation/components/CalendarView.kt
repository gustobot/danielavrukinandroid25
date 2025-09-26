package com.gusto.lunchmenu.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gusto.lunchmenu.presentation.models.FoodItem

@Composable
fun CalendarView(
    foodItemsByWeek: Map<Int, List<FoodItem>>,
    selectedFoodItem: FoodItem?,
    onFoodItemSelected: (FoodItem) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(foodItemsByWeek.entries.toList()) { (week, foodItems) ->
            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                Text(
                    text = "Week $week",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    foodItems.forEach { foodItem ->
                        FoodItemTileView(
                            foodItem = foodItem,
                            isSelected = foodItem == selectedFoodItem,
	                        isEnabled = true,
                            onItemClick = { onFoodItemSelected(foodItem) }
                        )
                    }
                }
            }
        }
    }
}
