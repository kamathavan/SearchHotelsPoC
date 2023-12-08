package com.app.test.search.favouritehotellist.repository

import com.app.test.domain.search.models.Hotel
import kotlinx.coroutines.flow.Flow

interface FavouritesRepository {
    suspend fun insertFavouriteHotel(hotel: Hotel)

    suspend fun getFavouriteHotel(): Flow<List<Hotel>>

    suspend fun deleteFavouriteHotel(hotelId:Hotel)
}