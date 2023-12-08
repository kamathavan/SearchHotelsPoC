package com.app.test.search.searchhistory.mapper

import com.app.test.domain.search.models.Hotel
import com.app.test.domain.search.models.HotelGeoLocation
import com.app.test.search.searchhistory.entity.HotelEntity

internal fun Hotel.toHotelEntity(): HotelEntity {
    return HotelEntity(
        hotelId = hotelId,
        hotelName = hotelName,
        address = address,
        locationName = locationName,
        hotelScorePoint = hotelScorePoint,
        hotelContactNumber = contactNumber,
        hotelGeoLocationLat = geoLocation.lat,
        hotelGeoLocationLong = geoLocation.long
    )
}

internal fun HotelEntity.toHotel(): Hotel {
    return Hotel(
        hotelId = hotelId,
        hotelName = hotelName,
        address = address,
        locationName = locationName,
        hotelScorePoint = hotelScorePoint,
        geoLocation = HotelGeoLocation(hotelGeoLocationLat, hotelGeoLocationLong),
        contactNumber = hotelContactNumber,
        locationId = 0
    )
}

internal fun List<HotelEntity>.toHotels(): List<Hotel> {
    return map { it.toHotel() }
}




