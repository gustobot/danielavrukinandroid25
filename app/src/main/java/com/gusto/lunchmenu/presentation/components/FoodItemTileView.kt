package com.gusto.lunchmenu.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gusto.lunchmenu.R
import com.gusto.lunchmenu.data.FoodItem
import com.gusto.lunchmenu.data.Weekday
import com.gusto.lunchmenu.ui.theme.MyApplicationTheme
import com.gusto.lunchmenu.ui.theme.PurpleGrey80

@Composable
fun FoodItemTileView(
	item: FoodItem,
	modifier: Modifier = Modifier,
) {
	Column(
		modifier = modifier
			.background(
				color = PurpleGrey80,
				shape = RoundedCornerShape(24.dp),
			)
			.padding(16.dp),
		verticalArrangement = Arrangement.SpaceBetween,
		horizontalAlignment = Alignment.CenterHorizontally,
		content = {
			Text(
				text = item.name,
				fontSize = 24.sp,
				modifier = Modifier.padding(4.dp),
			)

			Spacer(
				modifier = Modifier.height(16.dp),
			)

			Image(
				painter = painterResource(item.imageResId),
				contentDescription = null,
			)
		},
	)
}

@Preview
@Composable
fun FoodItemTileViewPreview() {
	MyApplicationTheme {
		FoodItemTileView(
			item = FoodItem(
				week = 1,
				weekday = Weekday.MONDAY,
				name = "Chicken and waffles",
				imageResId = R.drawable.chicken,
			)
		)
	}
}
