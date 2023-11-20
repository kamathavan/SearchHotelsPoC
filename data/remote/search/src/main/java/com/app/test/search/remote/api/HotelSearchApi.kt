package com.app.test.search.remote.api

import com.app.test.search.remote.responsemodel.HotelSearchResultResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HotelSearchApi {
    @GET(SEARCH_ENDPOINT)
    suspend fun searchHotels(
        @Query("query") query: String,
        @Query("lang") lang: String = "en",
        @Query("lookFor") lookFor: String = "hotel",
        @Query("limit") limit: Int = 20
    ): HotelSearchResultResponse

    companion object {
        const val TIME_OUT = 30
        const val BASE_URL = "https://engine.hotellook.com/"
        const val SEARCH_ENDPOINT = "api/v2/lookup.json"
    }
}
