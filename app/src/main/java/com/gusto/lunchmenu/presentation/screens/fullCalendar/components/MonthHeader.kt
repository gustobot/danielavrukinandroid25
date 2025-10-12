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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gusto.lunchmenu.ui.util.ThemedPreview
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

@Preview(name = "Current Month")
@Composable
private fun MonthHeaderPreview() {
	ThemedPreview {
		MonthHeader(yearMonth = YearMonth.now())
	}
}

@Preview(name = "Different Month")
@Composable
private fun MonthHeaderPreview_DifferentMonth() {
	ThemedPreview {
		MonthHeader(yearMonth = YearMonth.of(2024, 1)) // January 2024
	}
}

@Preview(name = "Tablet Preview", device = Devices.NEXUS_7)
@Composable
private fun MonthHeaderPreview_Tablet() {
	ThemedPreview {
		MonthHeader(yearMonth = YearMonth.of(2025, 8)) // August 2025
	}
}
