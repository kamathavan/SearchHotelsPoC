package com.app.test.search.remote.datasource

import com.app.test.search.remote.responsemodel.HotelSearchResultResponse

interface HotelSearchDataSource {
    suspend fun searchResultsForHotel(searchQuery: String): HotelSearchResultResponse
}