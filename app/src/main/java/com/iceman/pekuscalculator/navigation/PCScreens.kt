package com.iceman.pekuscalculator.navigation

import androidx.annotation.StringRes
import com.iceman.pekuscalculator.R

enum class PCScreens(@StringRes val title: Int) {
    Calculator(title = R.string.app_name),
    History(title = R.string.history_title),
}