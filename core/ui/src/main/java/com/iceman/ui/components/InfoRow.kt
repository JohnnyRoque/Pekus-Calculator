package com.iceman.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.iceman.network.request.MathRequest
import com.iceman.network.response.CalculateMathResponse
import com.iceman.ui.R

@Composable
fun InfoRow(value: CalculateMathResponse, modifier: Modifier = Modifier, onclick : (String) -> Unit) {
    Row(
        modifier = modifier.fillMaxWidth(),
    ) {
        DataText(value)
        IconButton(onClick = {onclick("${value.id}")}) {
            Icon(Icons.Default.Delete, stringResource(R.string.delete_text))
        }
    }

}
