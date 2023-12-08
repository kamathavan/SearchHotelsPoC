package com.app.test.search.favouritehotellist.usecase

import com.app.test.domain.search.models.Hotel
import com.app.test.search.favouritehotellist.repository.FavouritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFavouriteHotelsUseCase @Inject constructor(private val repository: FavouritesRepository) {
    suspend operator fun invoke(): Flow<List<Hotel>> = repository.getFavouriteHotel()
}