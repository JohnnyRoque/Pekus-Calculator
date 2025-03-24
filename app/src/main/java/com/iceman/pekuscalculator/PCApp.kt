package com.iceman.pekuscalculator

import android.R.attr.name
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.iceman.calculator.CalculatorScreen
import com.iceman.calculator.CalculatorViewModel
import com.iceman.pekuscalculator.navigation.PCScreens
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopperDriveAppBar(
    currentScreen: PCScreens,
    canNavigateUp: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {

    TopAppBar(
        title = {},
        colors = TopAppBarDefaults.topAppBarColors()
            .copy(containerColor = MaterialTheme.colorScheme.primary),
        modifier = modifier,
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


@Composable
fun PCApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen =
        PCScreens.valueOf(backStackEntry?.destination?.route ?: PCScreens.Calculator.name)

    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        ShopperDriveAppBar(
            currentScreen = currentScreen,
            canNavigateUp = navController.previousBackStackEntry != null,
            navigateUp = { navController.navigateUp() })
    }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = PCScreens.Calculator.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(PCScreens.Calculator.name) {
                CalculatorScreen(viewModel = koinViewModel() ) {
                    navController.navigate((PCScreens.History.name))

                }
            }
        }
    }
}

