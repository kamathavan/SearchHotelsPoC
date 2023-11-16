package com.app.test.search.place.ui.searchlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.test.domain.search.models.Hotel
import com.app.test.search.place.ui.Screens
import com.app.test.search.place.ui.model.HotelSearchUiState
import com.app.test.search.place.ui.searchdetails.HotelDetailsScreen

@Composable
fun SearchHomeScreen(searchViewModel: HotelSearchListViewModel = hiltViewModel()) {

    val state = searchViewModel.uiState.observeAsState()

    state.value?.let { state ->
        when (state) {
            is HotelSearchUiState.Loading -> {
                LoadingContent()
            }

            is HotelSearchUiState.Success -> {
                HotelNavigation(state.hotels)
            }

            is HotelSearchUiState.Error -> {
                ErrorScreen(state.message)
            }
        }
    }
}

@Composable
fun HotelNavigation(hotels: List<Hotel>) {
    val hotelNavigationController = rememberNavController()

    NavHost(navController = hotelNavigationController, startDestination = Screens.HotelList.route) {

        composable(route = Screens.HotelDetails.route) {
            ShowHotelSearchList(
                navigationController = hotelNavigationController,
                hotels = hotels,
            )
        }

        composable(route = Screens.HotelDetails.route){
            HotelDetailsScreen()
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowHotelSearchList(
    navigationController: NavController,
    hotels: List<Hotel>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

    }
    LazyColumn(
        modifier = Modifier.padding(10.dp),
        userScrollEnabled = true,
    ) {
        items(hotels) { hotel ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(5.dp),
                onClick = {

                }
            ) {
                Text(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                    text = hotel.hotelName,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}