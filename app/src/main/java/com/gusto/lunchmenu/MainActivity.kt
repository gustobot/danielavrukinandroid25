package com.gusto.lunchmenu

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.gusto.lunchmenu.databinding.ActivityMainBinding
import com.gusto.lunchmenu.presentation.screens.fullCalendar.FullCalendarScreen
import com.gusto.lunchmenu.ui.theme.MyApplicationTheme

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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
//        setupFragmentUI()
    }

    private fun setupFragmentUI() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }

    private fun setupComposeUI() {
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
	                FullCalendarScreen(
		                modifier = Modifier
			                .fillMaxSize()
			                .padding(innerPadding),
	                )
                }
            }
        }
    }
}