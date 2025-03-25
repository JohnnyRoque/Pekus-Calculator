package com.iceman.designsystem.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun ShopperTextField(
    modifier: Modifier = Modifier,
    userText: TextFieldValue,
    keyboardOption: KeyboardOptions,
    onTextChange: (TextFieldValue) -> Unit
) {
    val hasNumeric = userText.text.any { !it.isDigit() }

    Column(modifier) {
        TextField(
            keyboardOptions = keyboardOption.copy(keyboardType = KeyboardType.Number,imeAction = ImeAction.Next),
            isError =  hasNumeric,
            modifier = Modifier,
            onValueChange = { onTextChange(it) },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.LightGray.copy(0.2f),
                focusedContainerColor = Color.LightGray.copy(0.2f),
                errorContainerColor = Color.LightGray.copy(0.2f),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            value = userText,
            singleLine = true,
        )

    }

}

@Preview
@Composable
fun TextFieldPreview() {
    MaterialTheme {
        ShopperTextField(
            userText = TextFieldValue(""),
            keyboardOption = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)){}
    }
}

