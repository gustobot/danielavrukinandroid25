package com.gusto.lunchmenu

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.gusto.lunchmenu.presentation.screens.fullCalendar.FullCalendarScreen
import com.gusto.lunchmenu.ui.theme.MyApplicationTheme

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		/**
		 * You are welcome make use of Fragments or Compose for your UI
		 * The decision made will not affect the assessment
		 * Please utilize whatever you are most comfortable with
		 * Below are two functions that can be used to get started
		 * Uncomment one and remove the other
		 */
		setupComposeUI()
	}

	private fun setupComposeUI() {
		enableEdgeToEdge()
		setContent {
			MyApplicationTheme {
				FullCalendarScreen(
					modifier = Modifier.fillMaxSize(),
				)
			}
		}
	}
}
