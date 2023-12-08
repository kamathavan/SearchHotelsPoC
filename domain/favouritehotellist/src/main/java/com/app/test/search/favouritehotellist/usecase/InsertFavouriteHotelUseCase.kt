package com.app.test.search.favouritehotellist.usecase

import com.app.test.domain.search.models.Hotel
import com.app.test.search.favouritehotellist.repository.FavouritesRepository
import javax.inject.Inject

class InsertFavouriteHotelUseCase @Inject constructor(
    private val favouritesRepository: FavouritesRepository
) {
    suspend operator fun invoke(hotel: Hotel) {
        return favouritesRepository.insertFavouriteHotel(hotel)
    }
}