package com.gusto.lunchmenu.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gusto.lunchmenu.ui.theme.MyApplicationTheme

@Composable
fun MainActivityPrompt(
	modifier: Modifier = Modifier,
) = Box(
	modifier = modifier,
	contentAlignment = Alignment.Center
) {
	Text("Your UI here, replace with your Composable")
}

@Preview
@Composable
fun MainActivityPromptPreview() {
	MyApplicationTheme {
		Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
			MainActivityPrompt(
				modifier = Modifier
					.fillMaxSize()
					.padding(innerPadding)
			)
		}
	}
}