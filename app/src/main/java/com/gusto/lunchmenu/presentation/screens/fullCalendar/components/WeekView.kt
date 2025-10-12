package com.gusto.lunchmenu.presentation.screens.fullCalendar.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gusto.lunchmenu.data.models.CalendarItem
import com.gusto.lunchmenu.presentation.components.FoodItemTileView
import java.time.DayOfWeek
import java.time.LocalDate

@Composable
fun WeekView(
	modifier: Modifier = Modifier,
	week: CalendarItem.WeekRow,
	today: LocalDate,
	selectedDate: LocalDate?,
	onDateSelected: (LocalDate) -> Unit,
) {
	// Create a map of the days in the week for easy lookup
	val daysByWeekDay = week.days.associateBy { it.date.dayOfWeek }

	// Define the work week explicitly
	val workWeek = listOf(
		DayOfWeek.MONDAY,
		DayOfWeek.TUESDAY,
		DayOfWeek.WEDNESDAY,
		DayOfWeek.THURSDAY,
		DayOfWeek.FRIDAY
	)

	// Use LazyRow for a horizontally scrollable container
	LazyRow(
		modifier = modifier
			.fillMaxWidth()
			.padding(vertical = 8.dp),
		horizontalArrangement = Arrangement.spacedBy(8.dp)
	) {
		// Iterate through the explicit list to ensure a consistent 5-day layout
		items(workWeek) { dayOfWeek ->
			val day = daysByWeekDay[dayOfWeek]

			if (day != null && day.foodItem != null) {
				FoodItemTileView(
					modifier = Modifier
						.width(160.dp) // Set a fixed width for each tile
						.height(280.dp), // Set a fixed height for each tile
					foodItem = day.foodItem,
					date = day.date,
					isSelected = day.date == selectedDate,
					onItemClick = { onDateSelected(day.date) },
					isEnabled = !day.date.isBefore(today)
				)
			} else {
				// Add a spacer for days that don't have a menu item (e.g., out of range)
				// This keeps the alignment consistent if a week is partially filled.
				Spacer(modifier = Modifier.width(160.dp))
			}
		}
	}
}