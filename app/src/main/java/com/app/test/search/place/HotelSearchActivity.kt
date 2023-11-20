package com.app.test.search.place

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.app.test.search.place.ui.searchlist.SearchHomeScreen
import com.app.test.search.place.ui.theme.SearchHotelTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HotelSearchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SearchHotelTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SearchHomeScreen()
                }
            }
        }
    }
}