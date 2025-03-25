package com.iceman.designsystem.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PekusButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled : Boolean,
    onClick: () -> Unit

) {
    Button(onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors()
            .copy(containerColor = MaterialTheme.colorScheme.primaryContainer, contentColor = MaterialTheme.colorScheme.onPrimaryContainer)
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

