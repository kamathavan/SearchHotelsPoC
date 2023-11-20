package com.app.test.search.place.ui.searchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.test.domain.search.models.Hotel
import com.app.test.domain.search.models.RequestState
import com.app.test.domain.search.usecase.GetHotelSearchResultUseCase
import com.app.test.search.place.ui.model.SearchFieldState
import com.app.test.search.place.ui.model.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelSearchListViewModel @Inject constructor(
    private val hotelSearchResultUseCase: GetHotelSearchResultUseCase
) : ViewModel() {

    private val _inputText: MutableStateFlow<String> =
        MutableStateFlow("")
    val inputText: StateFlow<String> = _inputText

    private val _searchFieldState: MutableStateFlow<SearchFieldState> =
        MutableStateFlow(SearchFieldState.Idle)
    val searchFieldState: StateFlow<SearchFieldState> = _searchFieldState

    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.IdleScreen)
    val viewState: StateFlow<ViewState> = _viewState


    @FlowPreview
    fun initialize() {
        viewModelScope.launch {
            inputText.debounce(500).collectLatest { input ->
                if (input.blankOrEmpty()) {
                    _viewState.update {
                        ViewState.IdleScreen
                    }
                    return@collectLatest
                }
                when (val result = hotelSearchResultUseCase.getHotelSearch(input)) {
                    is RequestState.SuccessState -> {
                        _viewState.update { ViewState.SearchResultsFetched(result.data) }
                    }

                    is RequestState.FailureState -> {
                        _viewState.update { ViewState.Error }
                    }
                }
            }
        }
    }

    fun updateInput(inputText: String) {
        _inputText.update { inputText }
        activateSearchField()

        if (inputText.blankOrEmpty().not()) {
            _viewState.update { ViewState.Loading }
        }
    }

    fun searchFieldActivated() {
        activateSearchField()
    }

    fun clearInput() {
        _viewState.update { ViewState.Loading }
        _inputText.update { "" }
        _searchFieldState.update { SearchFieldState.EmptyActive }
    }

    fun revertToInitialState() {
        _viewState.update { ViewState.IdleScreen }
        _inputText.update { "" }
        _searchFieldState.update { SearchFieldState.Idle }
    }

    fun keyboardHidden() {
        if (inputText.value.blankOrEmpty()) {
            _searchFieldState.update { SearchFieldState.EmptyActive }
        } else {
            _searchFieldState.update { SearchFieldState.WithInputActive }
        }
    }

    private fun activateSearchField() {
        if (inputText.value.blankOrEmpty().not()) {
            _searchFieldState.update { SearchFieldState.WithInputActive }
        } else {
            _searchFieldState.update { SearchFieldState.EmptyActive }
        }
    }

    private fun String.blankOrEmpty() = this.isBlank() || this.isEmpty()
}