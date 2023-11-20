package com.app.test.domain.search.usecase

import com.app.test.domain.search.repository.HotelSearchRepository
import javax.inject.Inject

class GetHotelSearchResultUseCase @Inject constructor(
    private val hotelSearchRepository: HotelSearchRepository
) {
    suspend fun getHotelSearch(search: String) = hotelSearchRepository.getHotelSearch(
        searchQuery = search
    )
}