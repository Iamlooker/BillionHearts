package com.looker.billionhearts.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember

@Stable
@Composable
fun formatPrice(
    price: Double,
    currency: Char = '$',
): String = remember(price) {
    "$currency%.2f".format(price)
}
