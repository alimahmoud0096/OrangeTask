package com.alihafez.orangetask.data.remote

import com.alihafez.orangetask.BuildConfig.API_KEY
import okhttp3.Interceptor
import okhttp3.Response


class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .url
            .newBuilder()
            .addQueryParameter(APP_ID, API_KEY)
            .build()

        val newRequest = chain.request().newBuilder()
            .url(request)
            .build()

        return chain.proceed(newRequest)
    }

    companion object {
        private const val APP_ID = "key"

    }
}