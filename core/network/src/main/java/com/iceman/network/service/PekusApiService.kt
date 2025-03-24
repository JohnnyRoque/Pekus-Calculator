package com.iceman.network.service

import com.iceman.network.interceptor.PekusCalInterceptor
import com.iceman.network.request.MathRequest
import com.iceman.network.response.CalculateMathResponse
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

private const val BASE_URL = "https://intranet.pekus.com.br/calcapi/"

interface PekusApiService {

    @POST("api/Calculadora")
    suspend fun calculateNewMath(@Body insertNewMathRequest: MathRequest): Response<Int>

    @GET("api/Calculadora")
    suspend fun getMathList(): List<CalculateMathResponse>

    @DELETE("api/Calculadora")
    suspend fun deleteMathCal(@Query("id") id: Int): Response<Unit>

    @GET("api/Calculadora")
    suspend fun clearMathList(): Response<Unit>
}

internal class PekusCalApiImpl() : NetworkDataSource {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // Or HEADERS, BASIC, NONE
    }
    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(PekusCalInterceptor(false))
        .build()


    val contentType = "application/json".toMediaType()
    val json = Json { ignoreUnknownKeys = true }

    private val networkApi = Retrofit.Builder().apply {
        baseUrl(BASE_URL)
        client(client)
        addConverterFactory(json.asConverterFactory(contentType))
    }.build().create(PekusApiService::class.java)

    override suspend fun calculateNewMath(
        newMathRequest: MathRequest,
        callback: (code: Boolean, result: String?, error: String?) -> Unit
    ) {

        try {
            networkApi.calculateNewMath(newMathRequest).apply {

                if (isSuccessful) {
                    callback(true, this.body().toString(), null)
                } else {
                    callback(false, null, null)
                }
            }
        } catch (e: Exception) {
            callback(false, null, e.localizedMessage)
        }
    }

    override suspend fun getMathList(): List<CalculateMathResponse> {
        return networkApi.getMathList()
    }

    override suspend fun deleteMathCal(
        id: Int, callback: (code: Boolean, error: String?) -> Unit
    ) {
        try {
            if (networkApi.deleteMathCal(id).isSuccessful) {
                callback(true, null)
            } else {
                callback(false, null)
            }
        } catch (e: Exception) {
            callback(false, e.localizedMessage)
        }
    }

    override suspend fun clearMathList(callback: (code: Boolean, error: String?) -> Unit) {
        try {
            if (networkApi.clearMathList().isSuccessful) {
                callback(true, null)
            } else {
                callback(false, null)
            }
        } catch (e: Exception) {
            callback(false, e.localizedMessage)
        }
    }
}


