package com.iceman.data.repository

import com.iceman.data.model.MathRepositoryModel
import com.iceman.network.request.MathRequest
import com.iceman.network.response.CalculateMathResponse
import com.iceman.network.service.NetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MathRepository(private val networkDataSource: NetworkDataSource) : MathRepositoryModel {

    override suspend fun calculateNewMath(
        insertNewMathRequest: MathRequest,
        callback: (code: Boolean, error: String?) -> Unit
    ) {
        return networkDataSource.calculateNewMath(insertNewMathRequest) { code, error ->
            if (code) {
                callback(true, null)
            } else {
                callback(false, error)
            }
        }

    }

    override suspend fun getMathList(): Flow<List<CalculateMathResponse>> = flow {
        emit(
            networkDataSource.getMathList()
        )
    }


    override suspend fun deleteMathCal(
        id: Int,
        callback: (code: Boolean, error: String?) -> Unit
    ) {
        return networkDataSource.deleteMathCal(id) { code, error ->
            if (code) {
                callback(true, null)
            } else {
                callback(false, error)
            }
        }
    }

    override suspend fun clearMathList(
        callback: (code: Boolean, error: String?) -> Unit
    ) {
        return networkDataSource.clearMathList() { code, error ->
            if (code) {
                callback(true, null)
            } else {
                callback(false, error)
            }
        }
    }
}