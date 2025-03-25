package com.iceman.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.iceman.designsystem.components.extension.formatDouble
import com.iceman.designsystem.components.extension.formatToDate
import com.iceman.network.response.CalculateMathResponse

@Composable
fun DataText(fields: CalculateMathResponse, modifier: Modifier = Modifier) {

    Column(modifier = Modifier.padding(8.dp)) {

        Row(
            modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,

            ) {
            Text(
                text = "${fields.id}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = fields.valorA.formatDouble(),
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = fields.valorB.formatDouble(),
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "${fields.operation}",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Left
            )

            Text(
                text = fields.result.formatDouble(),
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = fields.date.formatToDate(),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        HorizontalDivider()
    }
}