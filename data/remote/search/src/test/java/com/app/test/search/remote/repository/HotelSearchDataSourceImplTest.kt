package com.app.test.search.remote.repository

import com.app.test.search.remote.api.HotelSearchApi
import com.app.test.search.remote.datasource.HotelSearchDataSourceImpl
import com.app.test.search.remote.responsemodel.Hotel
import com.app.test.search.remote.responsemodel.HotelSearchResultResponse
import com.app.test.search.remote.responsemodel.Hotels
import com.app.test.search.remote.responsemodel.HotelsLocation
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HotelSearchDataSourceImplTest {

    private lateinit var hotelSearchDataSource: HotelSearchDataSourceImpl

    @MockK
    lateinit var hotelSearchAPI: HotelSearchApi

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        hotelSearchDataSource = HotelSearchDataSourceImpl(
            hotelSearchApi = hotelSearchAPI
        )
    }

    // region for unit test
    @Test
    fun `Given Hotel Search API, When Hotel search data sources called, Then return success HotelSearch response`() =
        runTest {
            //given
            coEvery {
                hotelSearchAPI.searchHotels(
                    query = "some_location",
                    lang = "en",
                    lookFor = "hotel",
                    limit = 20
                )
            } returns getSearchResponse()

            //When
            val result = hotelSearchDataSource.searchResultsForHotel(searchQuery = "some_location")

            // Then
            assertThat(result.status).isEqualTo("ok")
        }
    // endregion for unit testing

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