package com.app.test.search.remote.datasource

import com.app.test.search.remote.api.HotelSearchApi
import com.app.test.search.remote.responsemodel.HotelSearchResultResponse
import javax.inject.Inject

class HotelSearchDataSourceImpl @Inject constructor(
    private val hotelSearchApi: HotelSearchApi
) : HotelSearchDataSource {
    override suspend fun searchResultsForHotel(searchQuery: String): HotelSearchResultResponse {
        return hotelSearchApi.searchHotels(query = searchQuery)
    }
}