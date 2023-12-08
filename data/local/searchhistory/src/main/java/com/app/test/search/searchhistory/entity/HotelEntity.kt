package com.app.test.search.searchhistory.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = HotelEntity.TABLE_NAME)
data class HotelEntity(
    @PrimaryKey
    @ColumnInfo(name = "hotelId")
    val hotelId: String,
    @ColumnInfo(name = "hotelName")
    val hotelName: String,
    @ColumnInfo(name = "address")
    val address: String,
    @ColumnInfo(name = "locationName")
    val locationName: String,
    @ColumnInfo(name = "hotelScorePoint")
    val hotelScorePoint: String,
    @ColumnInfo(name = "hotelGeoLocationLat")
    val hotelGeoLocationLat: Double,
    @ColumnInfo(name = "hotelGeoLocationLong")
    val hotelGeoLocationLong: Double,
    @ColumnInfo(name = "contactNumber")
    val hotelContactNumber: String,
) {
    companion object {
        const val TABLE_NAME = "FAVOURITE_HOTEL"
    }
}