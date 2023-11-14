package com.app.test.search.remote.responsemodel

data class HotelSearchResultResponse(
    val status: String,
    val results: Hotels,
)

data class Hotels(
    val hotels: List<Hotel>
)

data class Hotel(
    val location: HotelsLocation,
    val id: String,
    val _score: Long,
    val locationId: Int,
    val label: String,
    val locationName: String,
    val fullName: String,
)

class HotelsLocation(
    val lat: Double,
    val lon: Double,
)
