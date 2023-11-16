package com.app.test.search.place.ui.searchlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(){
    Column {
        SearchView()
        Spacer(modifier = Modifier.height(16.dp))
        SearchHomeScreen()
    }
}