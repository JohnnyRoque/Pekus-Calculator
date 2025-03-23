package com.iceman.data.extension

import com.iceman.network.request.MathRequest
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

 fun MathRequest.makeNewMathRequest(): MathRequest {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
    val currentDateTime = LocalDateTime.now()
    val formattedDate = currentDateTime.format(formatter)

    return MathRequest(
        valorA = valorA,
        valorB = valorB,
        operation = operation,
        result = result,
        date = formattedDate
    )
}