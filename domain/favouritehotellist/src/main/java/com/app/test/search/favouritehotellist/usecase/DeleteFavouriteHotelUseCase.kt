package com.app.test.search.favouritehotellist.usecase

import com.app.test.domain.search.models.Hotel
import com.app.test.search.favouritehotellist.repository.FavouritesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteFavouriteHotelUseCase @Inject constructor(
    private val favouriteRepository: FavouritesRepository
) {
    suspend operator fun invoke(hotel: Hotel) = withContext(Dispatchers.IO){
        favouriteRepository.deleteFavouriteHotel(hotel)
    }
}