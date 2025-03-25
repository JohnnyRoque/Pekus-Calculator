package com.iceman.calculator

import android.R.attr.enabled
import android.R.attr.onClick
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.iceman.calculator.util.CalculatorScreenCommand
import com.iceman.calculator.util.CalculatorScreenCommand.SuccessAlert
import com.iceman.designsystem.components.PekusButton
import com.iceman.designsystem.components.PekusTextButton
import com.iceman.ui.components.ArithmeticsButtons
import com.iceman.ui.components.ValuesTextFieldRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen(
    modifier: Modifier = Modifier, viewModel: CalculatorViewModel, onClickNav: () -> Unit
) {
    val context = LocalContext.current
    val mathValues by viewModel.mathValues.collectAsState()
    val screenCommand by viewModel.command.collectAsState()
    val selectedArithmetic by viewModel.selectedArithmetic.collectAsState()
    val isButtonEnable by viewModel.isButtonEnabled.collectAsState()

    Column(modifier.fillMaxSize().padding(16.dp).background(MaterialTheme.colorScheme.background), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

        ValuesTextFieldRow(
            userTexts = mathValues,
            onValueOneChange = { viewModel.updateMathValue(it, true) },
            onValueTwoChange = { viewModel.updateMathValue(it, false) })

        ArithmeticsButtons(selectedArithmetic = selectedArithmetic, modifier = Modifier.padding(16.dp)) { viewModel.selectArithmetic(it) }

        Spacer(Modifier.size(24.dp))

        PekusButton(
            text = stringResource(R.string.confirm_text),
            enabled = isButtonEnable ,
            onClick = {
                viewModel.calculateValues(
                    valueA = mathValues.first.text.toDouble(),
                    valueB = mathValues.second.text.toDouble(),
                    arithmetic = checkNotNull(selectedArithmetic)
                )
            }
        )
        Spacer(Modifier.size(24.dp))

        PekusTextButton(
            stringResource(R.string.history_text),
            onClick = onClickNav
        )

        when (screenCommand) {
            is SuccessAlert -> {
                if (viewModel.openDialog) {
                    BasicAlertDialog(onDismissRequest = { viewModel.clearValues() }) {
                        Surface(
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight(),
                            shape = MaterialTheme.shapes.large,
                            tonalElevation = AlertDialogDefaults.TonalElevation
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    stringResource(
                                        R.string.success_message,
                                        (screenCommand as SuccessAlert).message
                                    )
                                )
                                Spacer(modifier = Modifier.height(24.dp))
                                TextButton(
                                    onClick = { viewModel.clearValues() },
                                    modifier = Modifier.align(Alignment.End)
                                ) { Text(stringResource(R.string.confirm_text)) }
                            }

                        }
                    }
                }
            }
            is CalculatorScreenCommand.FailureAlert -> {
                Toast.makeText(
                    context,
                    stringResource(R.string.error_message),
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.clearValues()

            }
            is CalculatorScreenCommand.Idle -> {}
        }
    }
}

