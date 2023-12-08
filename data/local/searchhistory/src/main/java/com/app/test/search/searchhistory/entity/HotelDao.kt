package com.app.test.search.searchhistory.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HotelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveWishToHotelList(
        hotel: HotelEntity
    )

    @Query("SELECT * FROM ${HotelEntity.TABLE_NAME}")
    fun getWishListHotels(): Flow<List<HotelEntity>>
}