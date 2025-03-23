package com.iceman.network.service

import com.iceman.network.request.MathRequest
import com.iceman.network.response.CalculateMathResponse

interface NetworkDataSource {

    suspend fun calculateNewMath(
        newMathRequest: MathRequest,
        callback: (code: Boolean, error: String?) -> Unit
    )

    suspend fun getMathList(): List<CalculateMathResponse>

    suspend fun deleteMathCal(
        id: Int, callback: (code: Boolean, error: String?) -> Unit
    )

    suspend fun clearMathList(callback: (code: Boolean, error: String?) -> Unit)
}