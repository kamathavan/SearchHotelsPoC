package com.app.test.domain.search.repository

import com.app.test.domain.search.models.Hotel
import com.app.test.domain.search.models.RequestState

interface HotelSearchRepository {
    suspend fun getHotelSearch(searchQuery: String): RequestState<List<Hotel>>
}