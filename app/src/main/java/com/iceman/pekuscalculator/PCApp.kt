package com.iceman.pekuscalculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.iceman.calculator.CalculatorScreen
import com.iceman.calculator.CalculatorViewModel
import com.iceman.calculator.util.CalculatorScreenCommand.SuccessAlert
import com.iceman.chart.MathChartScreen
import com.iceman.chart.MathChartViewModel
import com.iceman.pekuscalculator.navigation.PCScreens
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopperDriveAppBar(
    currentScreen: PCScreens,
    canNavigateUp: Boolean,
    navigateUp: () -> Unit,
    onClick: () -> Unit,
    isNotEmpty : Boolean,
    modifier: Modifier = Modifier,
) {

    TopAppBar(
        title = {},
        colors = TopAppBarDefaults.topAppBarColors()
            .copy(containerColor = MaterialTheme.colorScheme.primary),
        modifier = modifier,
        actions = {
            if (currentScreen == PCScreens.History && isNotEmpty ) {
                IconButton(onClick) {
                    Icon(
                        Icons.Default.Delete,
                        stringResource(com.iceman.ui.R.string.delete_text),
                    )
                }
            }
        },
        navigationIcon = {
            if (canNavigateUp) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        modifier = Modifier.size(50.dp),
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = stringResource(id = R.string.back_button),
                    )
                }
            }
        },
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PCApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val calculatorViewModel = koinViewModel<CalculatorViewModel>()
    val mathChartViewModel = koinViewModel<MathChartViewModel>()
    var isDialogOpen by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    val currentScreen =
        PCScreens.valueOf(backStackEntry?.destination?.route ?: PCScreens.Calculator.name)

    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        ShopperDriveAppBar(
            currentScreen = currentScreen,
            canNavigateUp = navController.previousBackStackEntry != null,
            navigateUp = { navController.navigateUp() },
            onClick = { isDialogOpen = !isDialogOpen},
            isNotEmpty = mathChartViewModel.fetchMathList.collectAsState().value.isNotEmpty()
        )
    }) { innerPadding ->
        if (isDialogOpen) {
            BasicAlertDialog(onDismissRequest = { isDialogOpen = false }) {
                Surface(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    shape = MaterialTheme.shapes.large,
                    tonalElevation = AlertDialogDefaults.TonalElevation
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {


                        Text(stringResource(R.string.clear_warning_message))

                        Spacer(modifier = Modifier.height(24.dp))
                        Row {
                            TextButton(
                                onClick = {
                                    isDialogOpen = false
                                },
                            ) { Text(stringResource(R.string.cancel_text)) }

                            TextButton(
                                onClick = {
                                    mathChartViewModel.clearMathChart(context = context)
                                    isDialogOpen = false
                                },
                            ) { Text(stringResource(com.iceman.calculator.R.string.confirm_text)) }
                        }
                    }
                }
            }
        }


        NavHost(
            navController = navController,
            startDestination = PCScreens.Calculator.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(PCScreens.Calculator.name) {
                CalculatorScreen(viewModel = calculatorViewModel) {
                    navController.navigate((PCScreens.History.name))

                }
            }
            composable(PCScreens.History.name) {
                MathChartScreen(viewModel = mathChartViewModel)
            }
        }
    }
}

