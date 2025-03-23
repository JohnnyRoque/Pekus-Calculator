package com.iceman.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.iceman.domain.R

enum class Arithmetics(val symbol: String, @DrawableRes val icon: Int, @StringRes val contentDescription : Int) {
    ADD("+",R.drawable.add_symbol,R.string.add_text ),
    SUBTRACTION("-",R.drawable.subtraction_symbol,R.string.subtraction_text),
    TIMES("*",R.drawable.multiply_symbol,R.string.multiply_text),
    DIVISION("/",R.drawable.devide_symbol,R.string.division_text)
}