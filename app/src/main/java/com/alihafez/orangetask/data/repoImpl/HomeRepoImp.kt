package com.alihafez.orangetask.data.repoImpl

import com.alihafez.orangetask.data.datasource.RemoteDataSource
import com.alihafez.orangetask.domain.DataError
import com.alihafez.orangetask.data.model.BooksRes
import com.alihafez.orangetask.domain.repository.HomeRepository
import javax.inject.Inject

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
class HomeRepoImp @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : HomeRepository {


    override suspend fun listBooks(q: String): com.alihafez.orangetask.domain.Result<BooksRes, DataError.Remote> {
        return remoteDataSource.listBooks(q = q)
    }
}