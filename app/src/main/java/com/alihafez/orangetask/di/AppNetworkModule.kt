package com.alihafez.orangetask.di

import com.alihafez.orangetask.data.remote.BooksApiService
import com.alihafez.orangetask.data.remote.HeaderInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppNetworkModule {


    @Provides
    @Singleton
    fun provideHeaderInterceptor(): HeaderInterceptor =
        HeaderInterceptor()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        headerInterceptor: HeaderInterceptor
    ): OkHttpClient {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .protocols(listOf(Protocol.HTTP_1_1))
            .addInterceptor(headerInterceptor)
            .addInterceptor(loggingInterceptor)
            .readTimeout(
                60,
                TimeUnit.SECONDS
            )
            .writeTimeout(
                60,
                TimeUnit.SECONDS
            )
            .connectTimeout(
                60,
                TimeUnit.SECONDS
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

        val gson = GsonBuilder().setLenient().create()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BooksApiService.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): BooksApiService {
        return retrofit.create(BooksApiService::class.java)

    }
}