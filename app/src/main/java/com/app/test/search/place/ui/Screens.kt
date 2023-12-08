package com.app.test.search.place.ui

sealed class Screens(
    val route: String,
    val screenName: String
) {
    data object HotelList : Screens(
        route = "hotel_list",
        screenName = "HotelList"
    )

    data object HotelDetails : Screens(
        route = "hotel_details",
        screenName = "Hotel Details"
    )

    data object HotelFavList : Screens(
        route = "hotel_fav_list",
        screenName = "Hotel Fav List"
    )
}
