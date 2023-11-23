package com.app.test.search.remote.mapper

import com.app.test.domain.search.models.Hotel
import com.app.test.domain.search.models.HotelGeoLocation
import com.app.test.search.remote.responsemodel.HotelSearchResultResponse
import com.app.test.search.remote.responsemodel.HotelsLocation
import javax.inject.Inject

class HotelSearchResponseMapper @Inject constructor() {

    fun transform(hotelSearchResponse: HotelSearchResultResponse): List<Hotel> {
        val hotelList = mutableListOf<Hotel>()
        hotelSearchResponse.results.hotels.forEach { hotelResp ->
            val hotel = Hotel(
                hotelId = hotelResp.id,
                hotelName = hotelResp.label,
                locationId = hotelResp.locationId,
                locationName = hotelResp.locationName,
                address = hotelResp.fullName,
                hotelScorePoint = hotelResp._score.toString(),
                geoLocation = getGeoLatLong(hotelResp.location),
                contactNumber = getMobileNumber()
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

    private fun getMobileNumber() : String {
        val number1 = (0..9).random()
        val number2 = (0..9).random()
        return "97883$number1" + "839$number2"
    }
}