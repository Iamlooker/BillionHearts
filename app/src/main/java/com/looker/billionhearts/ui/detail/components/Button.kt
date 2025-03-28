package com.looker.billionhearts.ui.detail.components

import android.R
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.looker.billionhearts.ui.theme.BillionHeartsTheme

@Composable
fun SizeButton(
    shoeSize: Int,
    isSelected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
    colors: ButtonColors = sizeSelectionButtonColors,
    isEnabled: Boolean = true,
) {
    val transition = updateTransition(isSelected)
    val strokeWidth by transition.animateDp {
        if (it) 2.dp else 1.dp
    }
    val borderColor by transition.animateColor {
        if (it) colors.contentColor else colors.disabledContentColor
    }
    Button(
        onClick = onSelect,
        enabled = isEnabled,
        colors = colors,
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(strokeWidth, borderColor),
        contentPadding = PaddingValues(15.dp),
        modifier = Modifier
            .size(width = 70.dp, height = 44.dp)
            .then(modifier),
    ) {
        Text(
            text = "UK $shoeSize",
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Composable
fun AddToBagButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: ButtonColors = addToBagButtonColors,
) {
    Button(
        onClick = onClick,
        colors = colors,
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp),
    ) {
        Text(
            text = "Add to Bag",
            style = MaterialTheme.typography.labelMedium,
        )
    }
}

private val sizeSelectionButtonColors
    @Composable
    get() = ButtonDefaults.textButtonColors(
        containerColor = Color.Unspecified,
        contentColor = MaterialTheme.colorScheme.onSurface,
        disabledContainerColor = MaterialTheme.colorScheme.surfaceContainer,
        disabledContentColor = MaterialTheme.colorScheme.outline,
    )

private val addToBagButtonColors
    @Composable
    get() = ButtonDefaults.textButtonColors(
        containerColor = MaterialTheme.colorScheme.inverseSurface,
        contentColor = MaterialTheme.colorScheme.inverseOnSurface,
    )

@Preview
@Composable
private fun SizeSelectionPreview() {
    BillionHeartsTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            SizeButton(8, false, {})
            SizeButton(9, false, {}, isEnabled = false)
            SizeButton(10, true, {})
        }
    }
}

@Preview
@Composable
private fun AddToBagPreview() {
    BillionHeartsTheme {
        AddToBagButton({})
    }
}

