package com.iceman.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
    contentDescription: Int,
    onClick: () -> Unit
) {

    Surface(
        shape = RoundedCornerShape(16.dp), // Adjust corner radius
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable(true, onClick = onClick),

        color = Color.LightGray
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                painter = painterResource(icon),
                contentDescription = stringResource(contentDescription),
                tint = Color.Black,
            )
        }
    }
}

