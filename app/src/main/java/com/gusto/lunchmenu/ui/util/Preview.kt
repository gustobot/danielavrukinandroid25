package com.gusto.lunchmenu.ui.util

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.gusto.lunchmenu.ui.theme.MyApplicationTheme

/**
 * A wrapper for Composable previews that applies the application theme and a Surface.
 * This ensures that previews have a consistent background and theming, reducing boilerplate.
 */
@Composable
fun ThemedPreview(content: @Composable () -> Unit) {
	MyApplicationTheme {
		Surface {
			content()
		}
	}
}
