package com.iceman.designsystem.components


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iceman.designsystem.components.extension.debounce

@Composable
fun PekusTextButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit

) {
    var clickCount by remember { mutableIntStateOf(0) }
    val onButtonClick = debounce<Unit> { clickCount++ }
    TextButton(
        modifier = modifier,
        onClick = { onButtonClick(onClick()) },
    )
    {
        Text(
            text = AnnotatedString(text, SpanStyle(textDecoration = TextDecoration.Underline)),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}


@Preview
@Composable
fun ShopperTextButtonPreview() {
    MaterialTheme {
        PekusTextButton("Histórico", Modifier.padding(16.dp)) {}

    }
}

