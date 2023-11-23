package com.app.test.search.place.ui

sealed class Screens(
    val route: String,
    val screenName: String
) {
    object HotelList : Screens(
        route = "hotel_list",
        screenName = "HotelList"
    )

    object HotelDetails : Screens(
        route = "hotel_details",
        screenName = "Hotel Details"
    )

    object HotelFavList : Screens(
        route = "hotel_fav_list",
        screenName = "Hotel Fav List"
    )
}
