package com.alihafez.orangetask.data.remote

import com.alihafez.orangetask.data.model.BooksRes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface BooksApiService {

    @GET("volumes")
    suspend fun listBooks(
        @Query("q") q: String,
    ): Response<BooksRes>


    companion object {
        const val BASE_URL = "https://www.googleapis.com/books/v1/"
    }
}