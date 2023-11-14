package com.app.test.search.remote.repository

import android.util.Log
import com.app.test.domain.search.models.Hotel
import com.app.test.domain.search.models.RequestState
import com.app.test.domain.search.repository.HotelSearchRepository
import com.app.test.search.remote.datasource.HotelSearchDataSource
import com.app.test.search.remote.mapper.HotelSearchResponseMapper
import javax.inject.Inject

class HotelSearchRepositoryImpl @Inject constructor(
    private val hotelDataSource: HotelSearchDataSource,
    private val mapper: HotelSearchResponseMapper,
) : HotelSearchRepository {
    override suspend fun getHotelSearch(searchQuery: String): RequestState<List<Hotel>> {
        return try {
            val response = hotelDataSource.searchResultsForHotel(searchQuery = searchQuery)
            if (response.status == "ok") {
                RequestState.SuccessState(data = mapper.transform(hotelSearchResponse = response))
            } else {
                RequestState.FailureState(error = Throwable("Unknown exception"))
            }
        } catch (exception: Exception) {
            Log.i("TAG", "getHotelSearch: --> ${exception.message}")
            RequestState.FailureState(error = Throwable("Something went wrong"))
        }
    }
}