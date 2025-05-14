package com.alihafez.orangetask.di

import com.alihafez.orangetask.data.datasource.RemoteDataSource
import com.alihafez.orangetask.data.datasource.RetrofitRemoteDataSource
import com.alihafez.orangetask.data.repoImpl.HomeRepoImp
import com.alihafez.orangetask.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {

    @Binds
    fun provideRepository(repo: HomeRepoImp): HomeRepository

    @Binds
    fun provideRemoteDataSource(dataSource: RetrofitRemoteDataSource): RemoteDataSource

}