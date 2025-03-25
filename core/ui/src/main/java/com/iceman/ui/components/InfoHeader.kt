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

@Composable
fun InfoHeader(vararg fields: String, modifier: Modifier = Modifier) {

    Column(modifier = Modifier.padding(8.dp)) {

        Row(
            modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,

            ) {
            Text(
                text = fields[0],
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = fields[1],
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Left
            )
            Text(
                text = fields[2],
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = fields[3],
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = fields[4],
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = fields[5],
                style = MaterialTheme.typography.bodyMedium
            )
        }
        HorizontalDivider()
    }
}