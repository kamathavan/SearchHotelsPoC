package com.app.test.search.searchhistory

import com.app.test.domain.search.models.Hotel
import com.app.test.search.favouritehotellist.repository.FavouritesRepository
import com.app.test.search.searchhistory.entity.HotelDao
import com.app.test.search.searchhistory.mapper.toHotelEntity
import com.app.test.search.searchhistory.mapper.toHotels
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val hotelDao: HotelDao
) : FavouritesRepository {
    override suspend fun insertFavouriteHotel(hotel: Hotel) {
        hotelDao.saveWishToHotelList(hotel.toHotelEntity())
    }

    override suspend fun getFavouriteHotel(): Flow<List<Hotel>> {
        return hotelDao.getWishListHotels().map { hotelEntity ->
            hotelEntity.toHotels()
        }
    }

    override suspend fun deleteFavouriteHotel(hotel: Hotel) {
        return hotelDao.deleteWishListHotel(hotel = hotel.toHotelEntity())
    }
}