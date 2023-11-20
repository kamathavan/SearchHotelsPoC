package com.app.test.search.place.ui.searchlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
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
import com.app.test.search.place.R
import com.app.test.search.place.ui.Screens
import com.app.test.search.place.ui.model.SearchFieldState
import com.app.test.search.place.ui.model.ViewState
import com.app.test.search.place.ui.searchdetails.HotelDetailsScreen
import com.app.test.search.place.ui.theme.Purple80
import com.app.test.search.place.ui.theme.color_dark_blue
import com.app.test.search.place.ui.theme.color_silver
import com.app.test.search.place.ui.theme.color_soft_white

const val HOTEL_ID_KEY = "hotelId"

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchHomeScreen(searchViewModel: HotelSearchListViewModel = hiltViewModel()) {

    val viewState = searchViewModel.viewState.collectAsState().value
    val searchFieldState = searchViewModel.searchFieldState.collectAsState().value
    val inputFieldState = searchViewModel.inputText.collectAsState().value

    val keyboardController = LocalSoftwareKeyboardController.current
    val keyboardState by keyboardAsState()

    LaunchedEffect(null) {
        searchViewModel.initialize()
    }

    SearchScreenLayout(
        viewState = viewState,
        searchFieldState = searchFieldState,
        inputText = inputFieldState,
        onSearchInputChanged = { input -> searchViewModel.updateInput(input) },
        onSearchInputClicked = { searchViewModel.searchFieldActivated() },
        onClearInputClicked = { searchViewModel.clearInput() },
        onChevronClicked = {
            searchViewModel.revertToInitialState()
            keyboardController?.hide()
        },
        onKeyboardHidden = { searchViewModel.keyboardHidden() },
        keyboardState = keyboardState
    )
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

@Composable
private fun SearchScreenLayout(
    viewState: ViewState,
    searchFieldState: SearchFieldState,
    inputText: String,
    onSearchInputChanged: (String) -> Unit,
    onSearchInputClicked: () -> Unit,
    onClearInputClicked: () -> Unit,
    onChevronClicked: () -> Unit,
    onKeyboardHidden: () -> Unit,
    keyboardState: Keyboard,
) {
    val hotelNavigationController = rememberNavController()
    var hotels = remember { mutableListOf<Hotel>()}

    NavHost(navController = hotelNavigationController, startDestination = Screens.HotelList.route) {
        composable(route = Screens.HotelList.route) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color_soft_white)
            ) {
                SearchInputField(
                    searchFieldState = searchFieldState,
                    inputText = inputText,
                    onClearInputClicked = onClearInputClicked,
                    onSearchInputChanged = onSearchInputChanged,
                    onClicked = onSearchInputClicked,
                    onChevronClicked = onChevronClicked,
                    onKeyboardHidden = onKeyboardHidden,
                    keyboardState = keyboardState,
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(color_silver.copy(alpha = 0.2f))
                )

                when (viewState) {
                    ViewState.IdleScreen -> {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                        }
                    }

                    ViewState.Error -> {
                        ErrorScreen(error = "Something went wrong")
                    }

                    ViewState.Loading -> {
                        LoadingContent()
                    }

                    ViewState.NoResults -> {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text(text = "No results for this input :(", color = Purple80)
                        }
                    }

                    is ViewState.SearchResultsFetched -> {
                        hotels = viewState.results.toMutableList()
                        SearchResultsList(
                            hotels,
                            hotelNavigationController
                        )

                    }
                }
            }
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

/*    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color_dark_blue)
    ) {
        SearchInputField(
            searchFieldState = searchFieldState,
            inputText = inputText,
            onClearInputClicked = onClearInputClicked,
            onSearchInputChanged = onSearchInputChanged,
            onClicked = onSearchInputClicked,
            onChevronClicked = onChevronClicked,
            onKeyboardHidden = onKeyboardHidden,
            keyboardState = keyboardState,
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(color_silver.copy(alpha = 0.2f))
        )

        when (viewState) {
            ViewState.IdleScreen -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = R.drawable.undraw_search),
                        contentDescription = "Illustration",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            ViewState.Error -> {
                ErrorScreen(error = "Something went wrong")
            }

            ViewState.Loading -> {
                LoadingContent()
            }

            ViewState.NoResults -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "No results for this input :(", color = color_soft_white)
                }
            }

            is ViewState.SearchResultsFetched -> {
                ShowSuccessSearchResult(hotels = viewState.results)

            }
        }
    }*/
}

@Composable
fun ShowSuccessSearchResult(hotels: List<Hotel>) {
    val hotelNavigationController = rememberNavController()
    val hotels = remember { mutableStateListOf<Hotel>()}

    NavHost(navController = hotelNavigationController, startDestination = Screens.HotelList.route) {

        composable(route = Screens.HotelList.route) {
            SearchResultsList(
                hotels,
                hotelNavigationController
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResultsList(items: List<Hotel>, navigationController: NavController) {
    LazyColumn(
        userScrollEnabled = true,
    ) {
        items(items) { hotel ->
            Card(modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(5.dp),
                onClick = {
                    navigationController.navigate(
                        route = "${Screens.HotelDetails.route}/${hotel.hotelId}"
                    )
                }) {
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

