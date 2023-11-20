package com.app.test.search.place.ui.searchdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.test.domain.search.models.Hotel
import com.app.test.domain.search.models.HotelGeoLocation
import com.app.test.search.place.R
import com.app.test.search.place.ui.theme.SearchHotelTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HotelDetailsScreen(hotel: Hotel) {
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                AppBar(title = stringResource(id = R.string.feature_hotel_detail_title))
            },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = hotel.hotelName,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    )
                }

                Title(contentTitle = "Address")

                ContentTitle(hotel.address)

                Title(contentTitle = "Location")

                ContentTitle(hotel.locationName)

                Title(contentTitle = "Hotel Rating Points")

                ContentTitle(hotel.hotelScorePoint)
            }
        }
    }
}

@Composable
fun Title(contentTitle: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = contentTitle,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun ContentTitle(contentTitle: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = contentTitle,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Start
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    TopAppBar(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,

                ) {
                Text(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(14.dp),
                )
            }

        },
        scrollBehavior = scrollBehavior
    )
}

@Preview(showBackground = true)
@Composable
fun HotelDetailsScreenPreview() {
    SearchHotelTheme {
        HotelDetailsScreen(
            hotel = Hotel(
                hotelId = "",
                hotelName = "Testing Hotel",
                locationId = 0,
                locationName = "",
                address = "",
                hotelScorePoint = "",
                geoLocation = HotelGeoLocation(
                    12.90, 45.90
                )
            )
        )
    }
}