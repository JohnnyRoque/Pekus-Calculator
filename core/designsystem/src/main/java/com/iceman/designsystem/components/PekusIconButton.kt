package com.iceman.designsystem.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun PekusIconButton(
    modifier: Modifier = Modifier,
    icon: Int,
    color: Color,
    contentDescription: Int,
    onClick: () -> Unit
) {

    Surface(
        shape = RoundedCornerShape(16.dp), // Adjust corner radius
        modifier = modifier.border(1.dp,Color.Black.copy(0.2f) ,RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .clickable(true, onClick = onClick),

        color = color
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                painter = painterResource(icon),
                contentDescription = stringResource(contentDescription),
                tint = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

