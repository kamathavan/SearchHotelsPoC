package com.app.test.search.place.ui.searchlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.test.domain.search.models.Hotel
import com.app.test.search.place.ui.model.HotelSearchUiState

@Composable
fun SearchHomeScreen(searchViewModel: HotelSearchListViewModel = hiltViewModel()) {

    val state = searchViewModel.uiState.observeAsState()

    state.value?.let {
        when (it) {
            is HotelSearchUiState.Loading -> {
                LoadingContent()
            }

            is HotelSearchUiState.Success -> {
                showHotelSearchList(it.hotels)
            }

            is HotelSearchUiState.Error -> {
                ErrorScreen(it.message)
            }
        }
    }
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
            Text(
                text = hotel.hotelName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}