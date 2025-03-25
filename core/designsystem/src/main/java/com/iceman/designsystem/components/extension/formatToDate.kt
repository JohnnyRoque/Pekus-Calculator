package com.iceman.designsystem.components.extension

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun String.formatToDate(): String {
    // Remove the milliseconds (if any) by trimming after the seconds
    val trimmedInput = this.split(".")[0]

    // Define the input and output date formats
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
    val outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")

    // Parse the trimmed input string to a LocalDateTime object
    val dateTime = LocalDateTime.parse(trimmedInput, inputFormatter)

    // Format the LocalDateTime to the desired format
    return dateTime.format(outputFormatter)
}