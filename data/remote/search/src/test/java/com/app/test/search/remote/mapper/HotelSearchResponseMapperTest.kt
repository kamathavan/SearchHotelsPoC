package com.app.test.search.remote.mapper

import com.app.test.search.remote.responsemodel.Hotel
import com.app.test.search.remote.responsemodel.HotelSearchResultResponse
import com.app.test.search.remote.responsemodel.Hotels
import com.app.test.search.remote.responsemodel.HotelsLocation
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class HotelSearchResponseMapperTest {

    private lateinit var hotelSearchResponseMapper: HotelSearchResponseMapper

    @Before
    fun setUp() {
        hotelSearchResponseMapper = HotelSearchResponseMapper()
    }

    @Test
    fun `Given Hotel Search response, When transform, Then return list Search Hotel Domain Response`() {
        //Given
        val hotelResponse = getSearchResponse()

        //When
        val actualResult = hotelSearchResponseMapper.transform(
            hotelSearchResponse = hotelResponse
        )
        //Then

        assertThat(actualResult.size).isEqualTo(2)

        val hotelId = "some_id1"
        val hotelName =  "some_hotel1"
        val scorePoint = "12"
        val address =  "some_hotel_full_name1"
        val locationId = 23
        val locationName = "some_location1"

        assertThat(actualResult[0].hotelId).isEqualTo(hotelId)
        assertThat(actualResult[0].hotelName).isEqualTo(hotelName)
        assertThat(actualResult[0].hotelScorePoint).isEqualTo(scorePoint)
        assertThat(actualResult[0].address).isEqualTo(address)
        assertThat(actualResult[0].locationId).isEqualTo(locationId)
        assertThat(actualResult[0].locationName).isEqualTo(locationName)

    }

    // region for stubbing response
    private fun getSearchResponse(): HotelSearchResultResponse {

        return HotelSearchResultResponse(
            status = "ok",
            results = Hotels(
                listOf(
                    Hotel(
                        location = HotelsLocation(lat = 11.244, lon = 21.56),
                        id = "some_id1",
                        _score = 12,
                        locationId = 23,
                        label = "some_hotel1",
                        locationName = "some_location1",
                        fullName = "some_hotel_full_name1"
                    ),
                    Hotel(
                        location = HotelsLocation(lat = 11.244, lon = 21.56),
                        id = "some_id2",
                        _score = 12,
                        locationId = 23,
                        label = "some_hotel2",
                        locationName = "some_location2",
                        fullName = "some_hotel_full_name2"
                    )
                )
            )
        )
    }
    //endregion
}