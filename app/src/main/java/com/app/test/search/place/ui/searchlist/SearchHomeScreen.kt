package com.app.test.search.place.ui.searchlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.test.domain.search.models.Hotel
import com.app.test.search.place.R
import com.app.test.search.place.ui.Screens
import com.app.test.search.place.ui.model.HotelSearchUiState
import com.app.test.search.place.ui.searchdetails.AppBar
import com.app.test.search.place.ui.searchdetails.HotelDetailsScreen

const val HOTEL_ID_KEY = "hotelId"

@Composable
fun SearchHomeScreen(searchViewModel: HotelSearchListViewModel = hiltViewModel()) {

    val state = searchViewModel.uiState.observeAsState()

    val searchViewModel = viewModel<HotelSearchListViewModel>()
    val searchText by searchViewModel.searchInputFieldText.collectAsState()

    state.value?.let { state ->
        when (state) {
            is HotelSearchUiState.Loading -> {
                LoadingContent()
            }

            is HotelSearchUiState.Success -> {
                HotelNavigation(state.hotels, searchViewModel, searchText)
            }

            is HotelSearchUiState.Error -> {
                ErrorScreen(state.message)
            }
        }
    }
}

@Composable
fun HotelNavigation(
    hotels: List<Hotel>,
    searchViewModel: HotelSearchListViewModel,
    searchText: String,
) {
    val hotelNavigationController = rememberNavController()

    NavHost(navController = hotelNavigationController, startDestination = Screens.HotelList.route) {

        composable(route = Screens.HotelList.route) {
            ShowHotelSearchList(
                navigationController = hotelNavigationController,
                hotels = hotels,
                searchViewModel,
                searchText
            )
        }

        composable(route = "${Screens.HotelDetails.route}/{$HOTEL_ID_KEY}") {
            val hotelId = it.arguments?.getString(HOTEL_ID_KEY)
            val hotel = hotels.first { hotel ->
                (hotel.hotelId == hotelId)
            }
            HotelDetailsScreen(
                hotel = hotel
            )
        }
    }

}

@Composable
fun SearchView(
    searchViewModel: HotelSearchListViewModel,
    searchString: String
) {
    OutlinedTextField(
        value = searchString,
        onValueChange = searchViewModel::onSearchTextChange,
        placeholder = { Text(text = "Enter Location") },
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (searchViewModel.getSearchText().isEmpty()) {
                speechIconView
            } else {
                ClearInputField(searchViewModel)
            }
        },
        singleLine = true,
        shape = CircleShape,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = { searchViewModel.getHotelSearch(searchString) },
        )
    )
}


@Composable
fun ClearInputField(viewModel: HotelSearchListViewModel) {
    IconButton(
        onClick = { viewModel.clearInput()},
    ) {
        Icon(
            Icons.Default.Clear,
            contentDescription = "",
            tint = Color.Black
        )
    }
}

val speechIconView = @Composable {
    LocalContext.current
    IconButton(
        onClick = { },
    ) {
        Icon(
            Icons.Filled.PlayArrow,
            contentDescription = "",
            tint = Color.Black
        )
    }
}

@Composable
fun AddButton() {
    var isWishlisted by remember { mutableStateOf(false) }

    IconToggleButton(
        checked = isWishlisted,
        onCheckedChange = {
            isWishlisted = !isWishlisted
        }
    ) {
        Icon(
            tint = Color.Black,
            modifier = Modifier.graphicsLayer {
                scaleX = 1.0f
                scaleY = 1.0f
            },
            imageVector = if (isWishlisted) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = null
        )
    }
}

@Composable
fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.align(alignment = Alignment.Center))
    }
}

@Composable
fun ErrorScreen(error: String) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
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
    searchViewModel: HotelSearchListViewModel,
    searchText: String
) {
    Column {
        AppBar(
            title = stringResource(id = R.string.app_name),
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .padding(16.dp)
        ) {
            SearchView(searchViewModel, searchText)

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                userScrollEnabled = true,
            ) {
                items(hotels) { hotel ->
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(top = 5.dp),
                        onClick = {
                            navigationController.navigate(
                                route = "${Screens.HotelDetails.route}/${hotel.hotelId}"
                            )
                        }) {
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
                            AddButton()
                        }
                    }
                }
            }
        }
    }


}

