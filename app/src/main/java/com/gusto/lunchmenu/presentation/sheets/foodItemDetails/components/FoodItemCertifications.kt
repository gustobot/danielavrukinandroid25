package com.gusto.lunchmenu.presentation.sheets.foodItemDetails.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gusto.lunchmenu.ui.util.ThemedPreview

@Composable
fun FoodItemCertifications(
	certifications: List<String>,
	modifier: Modifier = Modifier,
) {
	if (certifications.isNotEmpty()) {
		Column(modifier = modifier) {
			Text(
				text = "Dietary Information",
				style = MaterialTheme.typography.titleMedium,
				fontWeight = FontWeight.Bold,
			)
			Spacer(Modifier.height(8.dp))
			certifications.forEach { certification ->
				CertificationRow(text = certification)
			}
		}
	}
}

@Preview(name = "Single Certification")
@Composable
private fun FoodItemCertificationsPreview_Single() {
	ThemedPreview {
		FoodItemCertifications(
			certifications = listOf("Gluten-Free"),
			modifier = Modifier.padding(16.dp),
		)
	}
}

@Preview(name = "Multiple Certifications")
@Composable
private fun FoodItemCertificationsPreview_Multiple() {
	ThemedPreview {
		FoodItemCertifications(
			certifications = listOf("High-Protein", "Organic", "Contains Nuts"),
			modifier = Modifier.padding(16.dp),
		)
	}
}

@Preview(name = "No Certifications")
@Composable
private fun FoodItemCertificationsPreview_Empty() {
	ThemedPreview {
		FoodItemCertifications(
			certifications = emptyList(),
			modifier = Modifier.padding(16.dp),
		)
	}
}

@Preview(name = "Tablet", device = Devices.PIXEL_TABLET)
@Composable
private fun FoodItemCertificationsPreview_Tablet() {
	ThemedPreview {
		FoodItemCertifications(
			certifications = listOf("Vegan", "Gluten-Free", "Non-GMO"),
			modifier = Modifier.padding(16.dp),
		)
	}
}
