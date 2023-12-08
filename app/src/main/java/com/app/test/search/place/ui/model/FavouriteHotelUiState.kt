package com.app.test.search.place.ui.model

import com.app.test.domain.search.models.Hotel

data class FavouriteHotelUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isSuccess:Boolean = false,
    val hotels: List<Hotel> = emptyList(),
)