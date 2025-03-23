package com.iceman.network.interceptor

import com.iceman.network.BuildConfig
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import java.io.IOException

class PekusCalInterceptor(private val disableRetry: Boolean) : Interceptor {
    override fun intercept(chain: Chain): Response {
        var response = makeRequest(chain)

        if (disableRetry) {
            return response
        }
        var attempt = 0
        while (response.code == CODE_TIMEOUT && attempt < MAX_RETRY) {
            attempt++
            response = makeRequest(chain, attempt)
        }
        return response
    }

    private fun makeRequest(chain: Chain, attempt: Int = 0): Response {
        var tryOutTimeout = attempt
        try {
            val originalRequest = chain.request()
            val originalUrl = originalRequest.url

            // Add API key as a query parameter
            val newUrl = originalUrl.newBuilder()
                .addQueryParameter("apikey", BuildConfig.API_KEY)
                .build()

            // Build new request with modified URL
            val newRequest = originalRequest.newBuilder()
                .url(newUrl)
                .build()

            return chain.proceed(newRequest)
        } catch (ex: Exception) {
            ex.message?.let {
                if (it.contains(TIMEOUT) && tryOutTimeout < MAX_RETRY) {
                    tryOutTimeout++
                    return makeRequest(chain, tryOutTimeout)
                }
            }
            throw IOException(TIMEOUT)
        }
    }

    companion object {
        private const val CODE_TIMEOUT = 504
        private const val MAX_RETRY = 3
        private const val TIMEOUT = "timeout"
    }
}