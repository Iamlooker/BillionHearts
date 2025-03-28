package com.looker.billionhearts.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.looker.billionhearts.R

val avenirFontFamily = FontFamily(
    Font(
        resId = R.font.avenir_heavy,
        weight = FontWeight.Bold,
    ),
    Font(
        resId = R.font.avenir_black,
        weight = FontWeight.Black,
    ),
    Font(
        resId = R.font.avenir_medium,
        weight = FontWeight.Medium,
    ),
    Font(
        resId = R.font.avenir_roman,
        weight = FontWeight.Normal,
    ),
)

val avalonFontFamily = FontFamily(
    Font(
        resId = R.font.avalon_bold,
        weight = FontWeight.Bold,
    ),
    Font(
        resId = R.font.avalon_regular,
        weight = FontWeight.Normal,
    ),
)

val gothamFontFamily = FontFamily(
    Font(
        resId = R.font.gotham_bold,
        weight = FontWeight.Bold,
    ),
    Font(
        resId = R.font.gotham_medium,
        weight = FontWeight.Medium,
    ),
)

// Default Material 3 typography values
val baseline = Typography()

val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = gothamFontFamily),
    displayMedium = baseline.displayMedium.copy(fontFamily = gothamFontFamily),
    displaySmall = baseline.displaySmall.copy(fontFamily = gothamFontFamily),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = gothamFontFamily),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = gothamFontFamily),
    headlineSmall = baseline.bodyLarge.copy(
        fontFamily = avalonFontFamily,
        fontWeight = FontWeight.Bold,
    ),
    titleLarge = baseline.headlineMedium.copy(
        fontFamily = gothamFontFamily,
        fontWeight = FontWeight.Bold,
    ),
    titleMedium = baseline.titleMedium.copy(
        fontFamily = gothamFontFamily,
        fontWeight = FontWeight.Medium,
    ),
    titleSmall = baseline.titleSmall.copy(
        fontFamily = avenirFontFamily,
        fontWeight = FontWeight.Black,
    ),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = avenirFontFamily),
    bodyMedium = baseline.bodyMedium.copy(
        fontFamily = avenirFontFamily,
        fontWeight = FontWeight.Medium,
    ),
    bodySmall = baseline.bodySmall.copy(
        fontFamily = avenirFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    labelLarge = baseline.labelLarge.copy(
        fontFamily = avalonFontFamily,
        fontWeight = FontWeight.Normal,
    ),
    labelMedium = baseline.labelLarge.copy(
        fontFamily = avenirFontFamily,
        fontWeight = FontWeight.Bold,
    ),
    labelSmall = baseline.labelSmall.copy(fontFamily = avenirFontFamily),
)

