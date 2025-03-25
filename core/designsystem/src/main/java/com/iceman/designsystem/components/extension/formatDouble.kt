package com.iceman.designsystem.components.extension

import java.text.DecimalFormat


fun Double.formatDouble(): String {
    val df = DecimalFormat("0.00")
    return df.format(this)
}