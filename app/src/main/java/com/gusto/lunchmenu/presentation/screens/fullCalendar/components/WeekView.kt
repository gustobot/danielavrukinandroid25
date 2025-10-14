package com.gusto.lunchmenu.presentation.screens.fullCalendar.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gusto.lunchmenu.R
import com.gusto.lunchmenu.data.models.CalendarItem
import com.gusto.lunchmenu.data.models.Day
import com.gusto.lunchmenu.data.models.Weekday
import com.gusto.lunchmenu.presentation.components.FoodItemTileView
import com.gusto.lunchmenu.presentation.models.FoodItem
import com.gusto.lunchmenu.ui.util.ThemedPreview
import java.time.DayOfWeek
import java.time.LocalDate

@Composable
fun WeekView(
	modifier: Modifier = Modifier,
	week: CalendarItem.WeekRow,
	today: LocalDate,
	selectedDate: LocalDate?,
	onDateSelected: (LocalDate) -> Unit,
	lazyListState: LazyListState = rememberLazyListState(),
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

	Box(
		modifier = modifier.fillMaxWidth(),
		contentAlignment = Alignment.Center
	) {
		LazyRow(
			modifier = Modifier.padding(vertical = 8.dp),
			horizontalArrangement = Arrangement.spacedBy(8.dp),
			state = lazyListState,
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
}

// --- PREVIEWS (Corrected) ---

// Helper function to create dummy data for previews
private fun createPreviewWeek(
	startDate: LocalDate,
	foodItems: List<FoodItem?>,
): CalendarItem.WeekRow {
	val days = (0..4).map { i ->
		val date = startDate.plusDays(i.toLong())
		Day(date = date, foodItem = foodItems.getOrNull(i))
	}
	return CalendarItem.WeekRow(days = days)
}

private val previewFoodItems: List<FoodItem?> = listOf(
	FoodItem(1, Weekday.MONDAY, "Chicken & Waffles", R.drawable.chicken),
	FoodItem(1, Weekday.TUESDAY, "Tacos", 0),
	FoodItem(1, Weekday.WEDNESDAY, "Curry", 0),
	FoodItem(1, Weekday.THURSDAY, "Pizza", 0),
	FoodItem(1, Weekday.FRIDAY, "Sushi", 0)
)

@Preview(name = "Standard Week")
@Composable
private fun WeekViewPreview_Standard() {
	ThemedPreview {
		WeekView(
			week = createPreviewWeek(LocalDate.of(2024, 7, 22), previewFoodItems),
			today = LocalDate.of(2024, 7, 22),
			selectedDate = null,
			onDateSelected = {}
		)
	}
}

@Preview(name = "With Past Dates")
@Composable
private fun WeekViewPreview_WithPastDates() {
	ThemedPreview {
		WeekView(
			week = createPreviewWeek(LocalDate.of(2024, 7, 22), previewFoodItems),
			today = LocalDate.of(2024, 7, 24), // Wednesday
			selectedDate = null,
			onDateSelected = {}
		)
	}
}

@Preview(name = "With Selection")
@Composable
private fun WeekViewPreview_WithSelection() {
	ThemedPreview {
		WeekView(
			week = createPreviewWeek(LocalDate.of(2024, 7, 22), previewFoodItems),
			today = LocalDate.of(2024, 7, 22),
			selectedDate = LocalDate.of(2024, 7, 24), // Wednesday selected
			onDateSelected = {}
		)
	}
}

@Preview(name = "Partially Filled Week")
@Composable
private fun WeekViewPreview_PartiallyFilled() {
	val partialFoodItems = previewFoodItems.toMutableList()
	partialFoodItems[3] = null // Remove Thursday's item
	ThemedPreview {
		WeekView(
			week = createPreviewWeek(LocalDate.of(2024, 7, 22), partialFoodItems),
			today = LocalDate.of(2024, 7, 22),
			selectedDate = null,
			onDateSelected = {}
		)
	}
}

@Preview(name = "Tablet Preview", device = Devices.NEXUS_7)
@Composable
private fun WeekViewPreview_Tablet() {
	ThemedPreview {
		WeekView(
			week = createPreviewWeek(LocalDate.of(2024, 7, 22), previewFoodItems),
			today = LocalDate.of(2024, 7, 22),
			selectedDate = null,
			onDateSelected = {}
		)
	}
}
