package com.app.test.search.place.ui.model

import com.app.test.domain.search.models.Hotel

sealed class HotelSearchUiState {
    data object Loading : HotelSearchUiState()
    data class Success(val hotels: List<Hotel>) : HotelSearchUiState()
    data class Error(val message: String) : HotelSearchUiState()
}
