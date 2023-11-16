package com.app.test.search.place.ui.searchlist

import android.graphics.drawable.shapes.OvalShape
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.test.domain.search.models.Hotel
import com.app.test.search.place.ui.model.HotelSearchUiState

@Composable
fun SearchHomeScreen(searchViewModel: HotelSearchListViewModel = hiltViewModel()) {

    val state = searchViewModel.uiState.observeAsState()
    val focusManager = LocalFocusManager.current
        state.value?.let {
            when (it) {
                is HotelSearchUiState.Loading -> {
                    LoadingContent()
                }

                is HotelSearchUiState.Success -> {
                    showHotelSearchList(it.hotels)
                    focusManager.clearFocus()
                }

                is HotelSearchUiState.Error -> {
                    ErrorScreen(it.message)
                }
            }
        }
}
@Composable
fun SearchView() {
    val searchViewModel = viewModel<HotelSearchListViewModel>()
    val searchText by searchViewModel.searchText.collectAsState()
        TextField(value = searchText,
            onValueChange = searchViewModel::onSearchTextChange,
            placeholder = { Text(text = "Enter Location")},
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(15.dp)
                        .size(24.dp)
                )
            },
            singleLine = true,
            shape = RectangleShape,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = { searchViewModel.getHotelSearch(searchText)},
            ))

    }

@Composable
fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.align(alignment = Alignment.Center))
    }
}

@Composable
fun ErrorScreen(error: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = error,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun showHotelSearchList(hotels: List<Hotel>) {
    LazyColumn(
        modifier = Modifier.padding(10.dp),
        userScrollEnabled = true,
    ) {
        items(hotels) { hotel ->
            Card(modifier = Modifier
                .fillMaxWidth().padding(5.dp)
            ) {
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = hotel.hotelName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    maxLines = 1
                ) 
            }
        }
    }
}

