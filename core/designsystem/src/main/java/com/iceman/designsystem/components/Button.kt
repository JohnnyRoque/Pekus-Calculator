package com.iceman.designsystem.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import com.iceman.designsystem.components.extension.debounce

@Composable
fun PekusButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled : Boolean,
    onClick: () -> Unit

) {
    var clickCount by remember { mutableIntStateOf(0) }
    val onButtonClick = debounce<Unit> { clickCount++ }
    Button(
        { onButtonClick(onClick()) },
        enabled = enabled,
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors()
            .copy(containerColor = MaterialTheme.colorScheme.primaryContainer)
    )
    {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}


@Preview
@Composable
fun ShopperButtonPreview() {
    MaterialTheme{
        PekusButton("Confirmar", Modifier.padding(16.dp), true) {}

    }
}

