package com.app.test.search.place.di


import com.app.test.domain.search.repository.HotelSearchRepository
import com.app.test.search.remote.datasource.HotelSearchDataSource
import com.app.test.search.remote.datasource.HotelSearchDataSourceImpl
import com.app.test.search.remote.repository.HotelSearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindHotelSearchRepository(hotelSearchRepoImpl: HotelSearchRepositoryImpl): HotelSearchRepository

    @Binds
    abstract fun bindHotelSearchDataSource(hotelSearchDataSource: HotelSearchDataSourceImpl): HotelSearchDataSource

}