package com.app.test.domain.search.usecase

import com.app.test.domain.search.repository.HotelSearchRepository
import javax.inject.Inject

class GetHotelSearchResultUseCase @Inject constructor(
    private val hotelSearchRepository: HotelSearchRepository
) {
    suspend operator fun invoke(search: String) = hotelSearchRepository.getHotelSearch(
        searchQuery = search
    )
}