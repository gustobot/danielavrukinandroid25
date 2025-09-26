package com.gusto.lunchmenu.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gusto.lunchmenu.data.MenuRepository
import com.gusto.lunchmenu.presentation.models.FoodItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

// A data class to hold the combined date and menu item information
private data class CalendarDay(val date: LocalDate, val foodItem: FoodItem)

@Composable
fun CalendarLunchMenuScreen(
	// In a real app, inject this via a ViewModel
	menuRepository: MenuRepository = remember { MenuRepository() },
) {
	var calendarDays by remember { mutableStateOf<List<CalendarDay>>(emptyList()) }
	var isLoading by remember { mutableStateOf(true) }
	var selectedDate by remember { mutableStateOf<LocalDate?>(null) }

	// Load the menu and generate the calendar dates when the composable enters the composition
	LaunchedEffect(Unit) {
		isLoading = true
		menuRepository.loadMenu()
		val today = LocalDate.now()
		// Generate a list of workdays, e.g., 20 workdays before and after today
		calendarDays = generateCalendarDays(today, 20, menuRepository)
		isLoading = false
	}

	if (isLoading) {
		Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
			CircularProgressIndicator()
		}
	} else {
		LazyRow(
			modifier = Modifier.fillMaxSize(),
			contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp),
			horizontalArrangement = Arrangement.spacedBy(16.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			items(calendarDays, key = { it.date }) { day ->
				// We have a menu item for this date, so show the tile
				FoodItemTileView(
					// We can add the date to the name for extra clarity
					foodItem = day.foodItem.copy(
						name = "${day.foodItem.name}\n${
							day.date.format(
								DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
							)
						}"
					),
					date = day.date,
					isSelected = day.date == selectedDate,
					isEnabled = true,
					onItemClick = {
						// Toggle selection
						selectedDate = if (selectedDate == day.date) null else day.date
					}
				)
			}
		}
	}
}

/**
 * Helper function to generate a list of workdays around a central date.
 */
private fun generateCalendarDays(
	centerDate: LocalDate,
	dayRange: Int,
	repository: MenuRepository,
): List<CalendarDay> {
	return (-dayRange..dayRange).map { dayOffset ->
		centerDate.plusDays(dayOffset.toLong())
	}.mapNotNull { date ->
		// Get the food item for the date. If it's null (e.g., a weekend),
		// mapNotNull will filter it out automatically.
		repository.getFoodItemForDate(date)?.let { foodItem ->
			CalendarDay(date, foodItem)
		}
	}
}
