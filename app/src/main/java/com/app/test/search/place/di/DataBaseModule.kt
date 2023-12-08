package com.app.test.search.place.di

import android.content.Context
import com.app.test.search.searchhistory.database.HotelDatabase
import com.app.test.search.searchhistory.entity.HotelDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Singleton
    @Provides
    fun provideDataBaseModule(
        @ApplicationContext appContext: Context,
    ): HotelDatabase = HotelDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideHotelDao(
        hotelDatabase: HotelDatabase
    ):HotelDao = hotelDatabase.getHotelDao()
}