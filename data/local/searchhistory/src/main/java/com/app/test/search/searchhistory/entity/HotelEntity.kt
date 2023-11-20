package com.app.test.search.searchhistory.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = HotelEntity.TABLE_NAME)
data class HotelEntity(
    @PrimaryKey
    @ColumnInfo(name = "hotelId")
    var hotelId: String,
    @ColumnInfo(name = "hotelName")
    var hotelName: String,
    @ColumnInfo(name = "address")
    var address: String,
    @ColumnInfo(name = "locationName")
    val locationName: String,
    @ColumnInfo(name = "hotelScorePoint")
    val hotelScorePoint: String,
) {
    companion object {
        const val TABLE_NAME = "WishlistHotel"
    }

}