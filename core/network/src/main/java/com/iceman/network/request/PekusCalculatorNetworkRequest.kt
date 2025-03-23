package com.iceman.network.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MathRequest(
    @SerialName("id") val id: Int? = null,
    @SerialName("valorA") val valorA : Double,
    @SerialName("valorB") val valorB : Double,
    @SerialName("operacao") val operation : String?,
    @SerialName("resultado") val result : Double,
    @SerialName("dataCalculo") val date : String,
    )
