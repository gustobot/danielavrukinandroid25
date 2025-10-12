package com.gusto.lunchmenu.presentation.screens.fullCalendar.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Composable
fun MonthHeader(
	modifier: Modifier = Modifier,
	yearMonth: YearMonth,
) {
	val formatter = remember { DateTimeFormatter.ofPattern("MMMM yyyy") }
	Text(
		text = yearMonth.format(formatter),
		fontSize = 20.sp,
		fontWeight = FontWeight.Bold,
		modifier = modifier
			.fillMaxWidth()
			.background(MaterialTheme.colorScheme.surface)
			.padding(vertical = 8.dp)
	)
}