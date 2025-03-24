package com.iceman.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iceman.designsystem.components.PekusIconButton
import com.iceman.domain.model.Arithmetics

@Composable
fun ArithmeticsButtons(
    modifier: Modifier = Modifier,
    selectedArithmetic: Arithmetics?,
    onClick: (Arithmetics) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 124.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(Arithmetics.entries) {
            PekusIconButton(
                modifier = Modifier.size(40.dp, height = 60.dp),
                icon = it.icon,
                color = if (selectedArithmetic == it) Color.Cyan.copy(0.8f) else Color.White,
                contentDescription = it.contentDescription,
                onClick = { onClick(it) }
            )

        }
    }
}

@Composable
@Preview
fun PreviewPekusIconButton() {
    MaterialTheme {
        var selectedArithmetic by remember { mutableStateOf(Arithmetics.TIMES) }

        ArithmeticsButtons(
            selectedArithmetic = selectedArithmetic
        ) {
            selectedArithmetic = it
        }
    }
}