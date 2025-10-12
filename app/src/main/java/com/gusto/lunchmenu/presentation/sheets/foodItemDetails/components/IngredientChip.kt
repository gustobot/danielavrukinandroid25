package com.gusto.lunchmenu.presentation.sheets.foodItemDetails.components

import android.R.attr.text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun IngredientChip(
	modifier: Modifier = Modifier,
	text: String,
	isAllergen: Boolean,
) {
	val backgroundColor = if (isAllergen) {
		MaterialTheme.colorScheme.errorContainer
	} else {
		MaterialTheme.colorScheme.secondaryContainer
	}
	val textColor = if (isAllergen) {
		MaterialTheme.colorScheme.onErrorContainer
	} else {
		MaterialTheme.colorScheme.onSecondaryContainer
	}

	Text(
		text = text,
		modifier = modifier
			.background(color = backgroundColor, shape = CircleShape)
			.padding(horizontal = 12.dp, vertical = 6.dp),
		color = textColor,
		fontWeight = if (isAllergen) FontWeight.Bold else FontWeight.Normal,
		style = MaterialTheme.typography.bodyMedium,
	)
}