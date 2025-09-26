package com.gusto.lunchmenu.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gusto.lunchmenu.R
import com.gusto.lunchmenu.data.models.Weekday
import com.gusto.lunchmenu.presentation.models.FoodItem
import com.gusto.lunchmenu.ui.theme.MyApplicationTheme
import com.gusto.lunchmenu.ui.theme.PurpleGrey80
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun FoodItemTileView(
	foodItem: FoodItem,
	date: LocalDate,
	isSelected: Boolean,
	isEnabled: Boolean,
	onItemClick: () -> Unit,
	modifier: Modifier = Modifier,
) {
	val shape = RoundedCornerShape(24.dp)
	val borderModifier = if (isSelected) {
		Modifier.border(width = 2.dp, color = Color.Black, shape = shape)
	} else {
		Modifier
	}
	val alpha = if (isEnabled) 1F else 0.5F

	// Formatters for the date display
	val dayOfWeekFormatter = remember { DateTimeFormatter.ofPattern("EEE") }
	val dayOfMonthFormatter = remember { DateTimeFormatter.ofPattern("d") }

	Column(
		modifier = modifier
			.alpha(alpha)
			.clickable(
				onClick = onItemClick,
				enabled = isEnabled,
			)
			.background(
				color = PurpleGrey80,
				shape = shape,
			)
			.then(borderModifier)
			.padding(16.dp),
		verticalArrangement = Arrangement.SpaceBetween,
		horizontalAlignment = Alignment.CenterHorizontally,
	) {
		Column(horizontalAlignment = Alignment.CenterHorizontally) {
			Text(
				text = date.format(dayOfWeekFormatter).uppercase(),
				style = MaterialTheme.typography.bodySmall,
				fontWeight = FontWeight.Bold
			)
			Text(
				text = date.format(dayOfMonthFormatter),
				style = MaterialTheme.typography.headlineSmall,
				fontWeight = FontWeight.Bold
			)
		}

		Spacer(modifier = Modifier.height(8.dp))

		Text(
			text = foodItem.name,
			style = MaterialTheme.typography.bodyLarge,
			textAlign = TextAlign.Center,
			modifier = Modifier.padding(4.dp),
		)

		Spacer(
			modifier = Modifier.height(16.dp),
		)

		if (foodItem.imageResId != 0) {
			Image(
				painter = painterResource(foodItem.imageResId),
				contentDescription = foodItem.name,
			)
		}
	}
}

@Preview(name = "Enabled Selected")
@Composable
fun FoodItemTileViewEnabledSelectedPreview() {
	MyApplicationTheme {
		FoodItemTileView(
			foodItem = FoodItem(
				week = 1,
				weekday = Weekday.MONDAY,
				name = "Chicken and waffles",
				imageResId = R.drawable.chicken,
			),
			date = LocalDate.now(),
			isSelected = true,
			isEnabled = true,
			onItemClick = {},
		)
	}
}

@Preview(name = "Enabled Deselected")
@Composable
fun FoodItemTileViewEnabledDeselectedPreview() {
	MyApplicationTheme {
		FoodItemTileView(
			foodItem = FoodItem(
				week = 1,
				weekday = Weekday.MONDAY,
				name = "Chicken and waffles",
				imageResId = R.drawable.chicken,
			),
			date = LocalDate.now(),
			isSelected = false,
			isEnabled = true,
			onItemClick = {},
		)
	}
}

@Preview(name = "Disabled")
@Composable
fun FoodItemTileViewDisabledPreview() {
	MyApplicationTheme {
		FoodItemTileView(
			foodItem = FoodItem(
				week = 1,
				weekday = Weekday.MONDAY,
				name = "Chicken and waffles",
				imageResId = R.drawable.chicken,
			),
			date = LocalDate.now().minusDays(1),
			isSelected = false,
			isEnabled = false,
			onItemClick = {},
		)
	}
}