package com.example.pruebatecnicacargamos.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pruebatecnicacargamos.R
import com.example.pruebatecnicacargamos.models.Movie
import com.example.pruebatecnicacargamos.screens.viewmodels.DetailScreenViewModel
import com.example.pruebatecnicacargamos.screens.viewmodels.FavoriteMoviesViewModel

@Composable
fun FavoriteMoviesScreen(navController: NavController, context: Context){

    val favoriteViewModel = FavoriteMoviesViewModel(context)
    val detailStatus = favoriteViewModel.status.collectAsState()
    val lazyListState = rememberLazyGridState()

    val favoriteMoviesList: List<Movie> = detailStatus.value

    if (favoriteMoviesList.isNotEmpty()) {
        FavoriteContent(navController = navController, listMovies = favoriteMoviesList, lazyListState = lazyListState)

    }

}

@Composable
fun FavoriteContent(navController: NavController, listMovies: List<Movie>, lazyListState: LazyGridState){
    var searchText by remember { mutableStateOf("") } // State for the search text

    // Apply the filter to the list of movies based on the search text
    val filteredMovies = if (searchText.isNotEmpty()) {
        listMovies.filter { it.title.contains(searchText, ignoreCase = true) }
    } else {
        listMovies
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .padding(top = 5.dp, bottom = 5.dp, start = 50.dp, end = 50.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = null,
                modifier = Modifier.clickable {
                    // Clear the search text when the image is clicked
                    searchText = ""
                }
            )
            BasicTextField(
                value = searchText,
                onValueChange = { searchText = it }, // Update the search text state
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
            )
        }
        ImagesGrid(navController = navController, listMovies = filteredMovies, lazyListState = lazyListState)
    }
}