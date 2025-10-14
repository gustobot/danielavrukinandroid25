package com.gusto.lunchmenu.presentation.sheets.foodItemDetails.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gusto.lunchmenu.ui.util.ThemedPreview

@Composable
fun CertificationRow(
	modifier: Modifier = Modifier,
	text: String,
) {
	Row(
		verticalAlignment = Alignment.CenterVertically,
		modifier = modifier.padding(vertical = 4.dp),
	) {
		Icon(
			imageVector = Icons.Default.CheckCircle,
			contentDescription = null, // Decorative icon
			tint = MaterialTheme.colorScheme.primary,
			modifier = Modifier.size(20.dp),
		)
		Spacer(Modifier.padding(horizontal = 4.dp))
		Text(text = text, style = MaterialTheme.typography.bodyLarge)
	}
}

@Preview(name = "Default")
@Composable
private fun CertificationRowPreview() {
	ThemedPreview {
		CertificationRow(text = "Organic")
	}
}

@Preview(name = "Long Text")
@Composable
private fun CertificationRowPreview_LongText() {
	ThemedPreview {
		CertificationRow(text = "Certified by the California Certified Organic Farmers (CCOF)")
	}
}

@Preview(name = "Tablet", device = Devices.NEXUS_7)
@Composable
private fun CertificationRowPreview_Tablet() {
	ThemedPreview {
		CertificationRow(text = "Gluten-Free")
	}
}
