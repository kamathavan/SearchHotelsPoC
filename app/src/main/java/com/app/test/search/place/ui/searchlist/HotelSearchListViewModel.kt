package com.app.test.search.place.ui.searchlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.test.domain.search.models.RequestState
import com.app.test.domain.search.usecase.GetHotelSearchResultUseCase
import com.app.test.search.place.ui.model.HotelSearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelSearchListViewModel @Inject constructor(
    private val hotelSearchResultUseCase: GetHotelSearchResultUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<HotelSearchUiState>()
    val uiState: LiveData<HotelSearchUiState> = _uiState

    private val _searchInputField = MutableStateFlow("")
    val searchInputFieldText = _searchInputField.asStateFlow()

    fun onSearchTextChange(searchLocation: String) {
        _searchInputField.value = searchLocation
    }

    fun getSearchText(): String {
        return searchInputFieldText.value
    }

    init {
        getHotelSearch(location = "")
    }

    fun getHotelSearch(location: String) {
        _uiState.value = HotelSearchUiState.Loading
        viewModelScope.launch {
            when (val result = hotelSearchResultUseCase.getHotelSearch(location)) {
                is RequestState.SuccessState -> {
                    _uiState.value = HotelSearchUiState.Success(hotels = result.data)
                    Log.i("getHotelSearch", "getHotelSearch: ${result.data}")
                }

                is RequestState.FailureState -> {
                    _uiState.value = HotelSearchUiState.Error(message = result.error.toString())
                }

                else -> {

                }
            }
        }
    }

    fun clearInput() {
        _uiState.value = HotelSearchUiState.Loading
        _searchInputField.update { "" }
        _uiState.value = HotelSearchUiState.Success(hotels = emptyList())
    }

}