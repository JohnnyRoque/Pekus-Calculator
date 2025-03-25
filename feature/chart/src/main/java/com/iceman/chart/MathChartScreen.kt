package com.iceman.chart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.iceman.ui.components.InfoHeader
import com.iceman.ui.components.InfoRow

@Composable
fun MathChartScreen(
    modifier: Modifier = Modifier,
    onclick: (String) -> Unit,
    viewModel: MathChartViewModel
) {
    val mathListUiState by viewModel.fetchMathList.collectAsState()
    Column(modifier = modifier.padding(8.dp)) {

        InfoHeader(
            stringResource(R.string.id_text),
            stringResource(R.string.operation_text),
            stringResource(R.string.valor_a_text),
            stringResource(R.string.valor_b_text),
            stringResource(R.string.result_text),
            stringResource(R.string.data_text)
        )
        LazyColumn {
            items(mathListUiState.sortedBy { it.id }) { fields ->
                InfoRow(
                    value = fields,
                    onclick = onclick
                )
            }
        }
    }
}