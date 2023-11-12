package com.app.test.search.remote.mapper

import com.app.test.domain.search.models.Hotel
import com.app.test.domain.search.models.HotelGeoLocation
import com.app.test.search.remote.responsemodel.HotelSearchResultResponse
import com.app.test.search.remote.responsemodel.HotelsLocation

class HotelSearchResponseMapper {

    fun transform(hotelSearchResponse: HotelSearchResultResponse): List<Hotel> {
        val hotelList = mutableListOf<Hotel>()
        hotelSearchResponse.results.forEach { hotelResp ->
            val hotel = Hotel(
                hotelId = hotelResp.id,
                hotelName = hotelResp.label,
                locationId = hotelResp.locationId,
                locationName = hotelResp.locationName,
                address = hotelResp.fullName,
                hotelScorePoint = hotelResp._score.toString(),
                geoLocation = getGeoLatLong(hotelResp.location)
            )
            hotelList.add(hotel)
        }
        return hotelList
    }

    private fun getGeoLatLong(location: HotelsLocation): HotelGeoLocation {
        return HotelGeoLocation(
            lat = location.lat,
            long = location.lon
        )
    }
}