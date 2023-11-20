package com.app.test.search.place.ui.model

sealed interface SearchFieldState {
    object Idle : SearchFieldState
    object EmptyActive : SearchFieldState
    object WithInputActive : SearchFieldState
}