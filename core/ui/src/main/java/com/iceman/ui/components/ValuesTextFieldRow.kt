package com.iceman.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.iceman.designsystem.components.ShopperTextField

@Composable
fun ValuesTextFieldRow(
    modifier: Modifier = Modifier,
    userTexts: Pair<TextFieldValue, TextFieldValue>,
    onValueOneChange: (TextFieldValue) -> Unit,
    onValueTwoChange: (TextFieldValue) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
    ) {
            ShopperTextField(
                modifier = Modifier.weight(1f),
                userText = userTexts.first,
                keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Number,imeAction = ImeAction.Next),
                onTextChange = onValueOneChange
            )

            Spacer(Modifier.weight(0.5f))

        ShopperTextField(
            modifier = Modifier.weight(1f),
            userText = userTexts.second,
            keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Number,imeAction = ImeAction.Done),
            onTextChange = onValueTwoChange
        )
    }
}
@Composable
@Preview(showBackground = true)
fun ValuesTextFieldRowPreview(){
    MaterialTheme{
        ValuesTextFieldRow(userTexts = Pair(TextFieldValue(),TextFieldValue(),), onValueOneChange = {}, onValueTwoChange = {})
    }
}