package com.gusto.lunchmenu.data

import com.gusto.lunchmenu.data.models.Weekday
import com.gusto.lunchmenu.presentation.models.FoodItem
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjusters

/**
 * Maps calendar dates to food items
 *
 * @param dataSource the source of the lunch menu data
 */
class MenuRepository(
	private val dataSource: LunchMenuDataSource = LunchMenuDataSource(),
) {

	// A map for quick lookups: Week -> Weekday -> FoodItem
	private var menuLookup: Map<Int, Map<Weekday, FoodItem>> = emptyMap()

	/**
	 * An anchor date to calculate the menu cycle.
	 * Let's assume our 2-week menu cycle started on this Monday.
	 * This can be any past Monday.
	 */
	private val cycleAnchorDate: LocalDate = LocalDate.of(2024, 1, 1) // Monday, Jan 1, 2024

	/**
	 * Loads the menu data from the data source and prepares it for quick lookups.
	 * In a real app, this would be called from a ViewModel and the result would be cached.
	 */
	suspend fun loadMenu() {
		if (menuLookup.isEmpty()) {
			val menu = dataSource.getLunchMenu()
			// Create a more efficient lookup map: Map<Week, Map<Weekday, FoodItem>>
			menuLookup = menu.mapValues { (_, items) ->
				items.associateBy { it.weekday }
			}
		}
	}

	/**
	 * Gets the FoodItem for a specific calendar date.
	 *
	 * @param date The calendar date.
	 * @return The corresponding [FoodItem] or null if none is scheduled (e.g., on a weekend).
	 */
	fun getFoodItemForDate(date: LocalDate): FoodItem? {
		// Menu is not available on weekends
		if (date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY) {
			return null
		}

		// Find the Monday of the week for the given date
		val startOfWeek = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))

		// Calculate how many weeks have passed since our anchor date
		val weeksSinceAnchor = ChronoUnit.WEEKS.between(cycleAnchorDate, startOfWeek)

		// Determine if it's menu week 1 or 2 using the modulo operator.
		// We add 1 because our menu weeks are 1-indexed.
		val menuWeek = (weeksSinceAnchor % 2).toInt() + 1

		// Convert java.time.DayOfWeek to our domain-specific Weekday enum
		val weekday = Weekday.from(date.dayOfWeek) ?: return null

		return menuLookup[menuWeek]?.get(weekday)
	}
}
