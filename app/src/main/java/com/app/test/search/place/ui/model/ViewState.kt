package com.app.test.search.place.ui.model

import com.app.test.domain.search.models.Hotel

sealed interface ViewState {
    object IdleScreen : ViewState
    object Loading : ViewState
    object Error : ViewState
    object NoResults : ViewState
    data class SearchResultsFetched(val results: List<Hotel>) : ViewState
}