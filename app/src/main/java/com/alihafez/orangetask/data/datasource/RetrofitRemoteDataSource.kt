package com.alihafez.orangetask.data.datasource

import com.alihafez.core.data.remote.safeCall
import com.alihafez.orangetask.data.remote.BooksApiService
import com.alihafez.orangetask.domain.DataError
import com.alihafez.orangetask.domain.Result
import com.alihafez.orangetask.data.model.BooksRes
import javax.inject.Inject

/**
 * created by ِAli Mahmoud Abdelhafez on 17/4/25
 **/
class RetrofitRemoteDataSource @Inject constructor(private val apiService: BooksApiService) :
    RemoteDataSource {

    override suspend fun listBooks(
        q:String
    ): Result<BooksRes, DataError.Remote> {
        return safeCall {
            apiService.listBooks(q = q)
        }
    }

}