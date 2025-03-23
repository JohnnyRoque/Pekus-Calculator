package com.iceman.calculator

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import com.iceman.calculator.util.CalculatorScreenCommand.SuccessAlert
import com.iceman.designsystem.components.PekusButton
import com.iceman.ui.components.ArithmeticsButtons
import com.iceman.ui.components.ValuesTextFieldRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen(modifier: Modifier = Modifier, viewModel: CalculatorViewModel) {
    val context = LocalContext.current
    val mathValues by viewModel.mathValues.collectAsState()
    val screenCommand by viewModel.command.collectAsState()
    var openDialog by remember { mutableStateOf(true) }

    Column(modifier.fillMaxSize()) {

        ValuesTextFieldRow(userTexts = mathValues, onValueOneChange = {}) { }
        ArithmeticsButtons { viewModel.selectArithmetic(it) }

        PekusButton(
            text = "Confirmar",
            enabled =  (mathValues.first.text.isNotEmpty() && mathValues.second.text.isNotEmpty()),
            onClick = {viewModel.calculateValues(valueA = mathValues.first.text.toDouble(), valueB = mathValues.second.text.toDouble(), arithmetic = viewModel.selectedArithmetic )}
        )

        if (screenCommand is SuccessAlert) {
            if (openDialog) {
                BasicAlertDialog(onDismissRequest = { openDialog = false }) {
                    Surface(
                        modifier = Modifier.wrapContentWidth().wrapContentHeight(),
                        shape = MaterialTheme.shapes.large,
                        tonalElevation = AlertDialogDefaults.TonalElevation
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Dados armazenados com sucesso, ID de Armazenamento ${(screenCommand as SuccessAlert).message}")
                            Spacer(modifier = Modifier.height(24.dp))
                            TextButton(onClick = {
                                openDialog = false
                            }, modifier = Modifier.align(Alignment.End)) { Text("Confirm") }
                        }

                    }
                }
            } else {
                Toast.makeText(
                    context,
                    "Houve um erro inesperado, tente novamente.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

