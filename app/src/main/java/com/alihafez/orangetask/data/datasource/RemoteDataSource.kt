package com.alihafez.orangetask.data.datasource

import com.alihafez.orangetask.domain.DataError
import com.alihafez.orangetask.domain.Result
import com.alihafez.orangetask.data.model.BooksRes

interface RemoteDataSource {
    suspend fun listBooks(q: String): Result<BooksRes, DataError.Remote>

}