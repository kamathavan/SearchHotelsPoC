package com.app.test.search.searchhistory.entity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.test.domain.search.models.Hotel
import kotlinx.coroutines.flow.Flow

@Dao
interface HotelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveWishToHotelList(
        hotel: HotelEntity
    )

    @Query("SELECT * FROM ${HotelEntity.TABLE_NAME}")
    fun getWishListHotels(): Flow<List<HotelEntity>>

    @Delete
    fun deleteWishListHotel(hotel: HotelEntity)
}