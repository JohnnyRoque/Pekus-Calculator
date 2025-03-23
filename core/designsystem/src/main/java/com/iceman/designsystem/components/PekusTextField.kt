package com.iceman.designsystem.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun ShopperTextField(
    modifier: Modifier = Modifier,
    userText: TextFieldValue,
    keyboardActions: KeyboardActions,
    keyboardOption: KeyboardOptions,
    onTextChange: (TextFieldValue) -> Unit
) {

    Column {
        TextField(
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOption,
            modifier = modifier
                .fillMaxWidth(),
            onValueChange = { onTextChange(it) },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.LightGray,
                focusedContainerColor = Color.LightGray,
                errorContainerColor = Color.LightGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
            ),
            value = userText,
            singleLine = true,
        )
        HorizontalDivider(modifier = Modifier
            .size(4.dp)
            .width(IntrinsicSize.Max))
    }

}

@Preview
@Composable
fun TextFieldPreview() {
    MaterialTheme {
        ShopperTextField(
            userText = TextFieldValue(""),
            keyboardOption = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = {})) {}
    }
}

