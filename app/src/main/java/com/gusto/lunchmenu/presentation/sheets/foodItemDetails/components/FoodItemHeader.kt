package com.gusto.lunchmenu.presentation.sheets.foodItemDetails.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gusto.lunchmenu.ui.util.ThemedPreview

@Composable
fun FoodItemHeader(
	foodItemName: String,
	foodItemPrice: Double,
	modifier: Modifier = Modifier,
) {
	Row(
		modifier = modifier.fillMaxWidth(),
		horizontalArrangement = Arrangement.SpaceBetween,
		verticalAlignment = Alignment.CenterVertically,
	) {
		Text(
			text = foodItemName,
			style = MaterialTheme.typography.headlineMedium,
			fontWeight = FontWeight.Bold,
		)
		Text(
			text = "$%.2f".format(foodItemPrice),
			style = MaterialTheme.typography.headlineMedium,
			color = MaterialTheme.colorScheme.primary,
		)
	}
}

@Preview(name = "Default")
@Composable
private fun FoodItemHeaderPreview_Default() {
	ThemedPreview {
		FoodItemHeader(
			foodItemName = "Chicken & Waffles",
			foodItemPrice = 14.99,
			modifier = Modifier.padding(16.dp),
		)
	}
}

@Preview(name = "Long Name")
@Composable
private fun FoodItemHeaderPreview_LongName() {
	ThemedPreview {
		FoodItemHeader(
			foodItemName = "Super Extra Long Gourmet Chicken and Waffles with a side of Maple Syrup",
			foodItemPrice = 25.50,
			modifier = Modifier.padding(16.dp),
		)
	}
}

@Preview(name = "Tablet", device = Devices.PIXEL_TABLET)
@Composable
private fun FoodItemHeaderPreview_Tablet() {
	ThemedPreview {
		FoodItemHeader(
			foodItemName = "Super Extra Long Gourmet Chicken and Waffles with a side of Maple Syrup",
			foodItemPrice = 25.50,
			modifier = Modifier.padding(16.dp),
		)
	}
}

@Preview(name = "Zero Price")
@Composable
private fun FoodItemHeaderPreview_ZeroPrice() {
	ThemedPreview {
		FoodItemHeader(
			foodItemName = "Free Water",
			foodItemPrice = 0.0,
			modifier = Modifier.padding(16.dp),
		)
	}
}
