package com.iceman.data.model

import com.iceman.network.request.MathRequest
import com.iceman.network.response.CalculateMathResponse


interface MathRepositoryModel {

    suspend fun calculateNewMath(
        insertNewMathRequest: MathRequest, callback: (code: Boolean,  result: String?,error: String?) -> Unit
    )

  suspend   fun getMathList(): List<CalculateMathResponse>

    suspend fun deleteMathCal(
        id: Int, callback: (code: Boolean, error: String?) -> Unit
    )

    suspend fun clearMathList(
        callback: (code: Boolean, error: String?) -> Unit
    )
}