package com.alihafez.orangetask.domain.repository

import com.alihafez.orangetask.domain.DataError
import com.alihafez.orangetask.domain.Result
import com.alihafez.orangetask.data.model.BooksRes
import kotlinx.coroutines.flow.Flow


interface HomeRepository {
    suspend fun listBooks(q: String): Result<BooksRes, DataError.Remote>

}