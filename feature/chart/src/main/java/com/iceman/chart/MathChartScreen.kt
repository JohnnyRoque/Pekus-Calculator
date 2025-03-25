package com.iceman.chart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.iceman.ui.components.InfoRow

@Composable
fun MathChartScreen(
    modifier: Modifier = Modifier,
    viewModel: MathChartViewModel
) {
    val context = LocalContext.current
    val mathListUiState by viewModel.fetchMathList.collectAsState()
    Column(modifier = modifier.padding(8.dp)) {

        LazyColumn {
            items(mathListUiState.sortedBy { it.id }) { fields ->
                InfoRow(
                    value = fields,
                    onclick = {viewModel.deleteMath(it,context)}
                )
            }
        }
    }
}