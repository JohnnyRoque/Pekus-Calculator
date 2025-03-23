package com.iceman.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PekusIconButton(
    modifier : Modifier = Modifier,
    icon: ImageVector,
    contentDescription : String,
    onClick : () -> Unit
){

        Surface(
            shape = RoundedCornerShape(16.dp), // Adjust corner radius
            modifier = modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(16.dp))
                .clickable(true, onClick = onClick)
                .size(80.dp),

            color = Color.LightGray
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = icon,
                    contentDescription = contentDescription,
                    tint = Color.Black,
                    modifier = Modifier.size(40.dp) // Adjust icon size
                )
            }
    }
}

@Composable
@Preview
fun PreviewPekusIconButton(){
    MaterialTheme {
        PekusIconButton(Modifier, icon = Icons.Default.Add,"Somar"){}
    }
}