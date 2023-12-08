package com.app.test.search.place.ui.hotelfavlistscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.test.search.favouritehotellist.usecase.GetAllFavouriteHotelsUseCase
import com.app.test.search.place.ui.model.FavouriteHotelUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteHotelListViewModel @Inject constructor(
    private val getAllFavouriteHotelsUseCase: GetAllFavouriteHotelsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavouriteHotelUiState())
    val uiState: StateFlow<FavouriteHotelUiState> = _uiState

    init {
        getAllFavouriteHotels()
    }

    private fun getAllFavouriteHotels() {
        viewModelScope.launch {
            getAllFavouriteHotelsUseCase().collectLatest { hotels ->
                _uiState.update { favouriteHotelUiState ->
                    favouriteHotelUiState.copy(
                        isLoading = false,
                        isError = false,
                        isSuccess = true,
                        hotels = hotels
                    )
                }
            }
        }
    }
}