package com.iceman.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CalculateMathResponse (
    @SerialName("id") val id: Int,
    @SerialName("valorA") val valorA : Double,
    @SerialName("valorB") val valorB : Double,
    @SerialName("operacao") val operation : String?,
    @SerialName("resultado") val result : Double,
    @SerialName("dataCalculo") val date : String,
)