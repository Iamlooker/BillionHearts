package com.looker.billionhearts.ui.detail.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

@Composable
fun ExpandableText(
    state: ExpandableTextState,
    modifier: Modifier = Modifier,
) {
    Text(
        text = state.text,
        modifier = modifier.clickable(enabled = state.hasOverflow, onClick = state::onClick),
        maxLines = state.shownLines,
        onTextLayout = state::updateTextLayout,
    )
}

@Composable
fun rememberExpandableTextState(
    text: AnnotatedString,
    maxLines: Int = 3,
    boldTextStyle: TextStyle = LocalTextStyle.current.copy(fontWeight = FontWeight.Bold),
): ExpandableTextState = remember(text) {
    ExpandableTextState(
        fullText = text,
        maxLines = maxLines,
        boldTextStyle = boldTextStyle,
    )
}

@Immutable
class ExpandableTextState(
    private val fullText: AnnotatedString,
    private val maxLines: Int,
    private val boldTextStyle: TextStyle,
) {
    var shownLines: Int by mutableIntStateOf(maxLines)
        private set

    var text: AnnotatedString by mutableStateOf(fullText)
        private set

    var hasOverflow: Boolean by mutableStateOf(false)
        private set

    var isExpanded: Boolean by mutableStateOf(false)
        private set

    // Cache once
    private var collapsedText = text

    @Stable
    fun updateTextLayout(result: TextLayoutResult) {
        if (hasOverflow && result.hasVisualOverflow) return
        if (result.hasVisualOverflow) {
            hasOverflow = true
            collapsedText = buildAnnotatedString {
                append(fullText.substring(0, result.getLineEnd(maxLines - 1) - 8))
                append("...")
                withStyle(boldTextStyle.toSpanStyle()) {
                    append("MORE")
                }
            }
            text = collapsedText
        }
    }

    @Stable
    fun onClick() {
        isExpanded = !isExpanded
        text = if (isExpanded) fullText else collapsedText
        shownLines = if (isExpanded) Int.MAX_VALUE else maxLines
    }
}
