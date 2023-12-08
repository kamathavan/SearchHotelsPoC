package com.app.test.search.place.ui.hotelfavlistscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.test.domain.search.models.Hotel
import com.app.test.search.place.R
import com.app.test.search.place.ui.searchdetails.AppBar
import com.app.test.search.place.ui.searchlist.ErrorScreen
import com.app.test.search.place.ui.searchlist.LoadingContent

@Composable
fun ShowHotelFavListScreen() {
    val favouriteHotelListViewModel: FavouriteHotelListViewModel = hiltViewModel()
    val favouriteUiState = favouriteHotelListViewModel.uiState.collectAsState().value

    Column {
        AppBar(
            title = stringResource(R.string.favourite_hotel_list),
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            if (favouriteUiState.isLoading) {
                LoadingContent()
            }

            if (favouriteUiState.isSuccess) {
                val favouriteHotelList = favouriteUiState.hotels
                ShowFavouriteList(favouriteHotelList = favouriteHotelList)
            }

            if(favouriteUiState.isError){
                ErrorScreen(stringResource(R.string.something_went_wrong))
            }

        }
    }
}

@Composable
fun ShowFavouriteList(favouriteHotelList: List<Hotel>) {
    LazyColumn(
        userScrollEnabled = true,
    ) {
        items(favouriteHotelList) { hotel ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(top = 5.dp),
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier
                            .padding(2.dp)
                            .weight(1f, fill = false),
                        text = hotel.hotelName,
                        maxLines = 1,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

