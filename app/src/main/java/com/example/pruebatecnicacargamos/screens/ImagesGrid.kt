package com.example.pruebatecnicacargamos.screens

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pruebatecnicacargamos.constants.Constants
import com.example.pruebatecnicacargamos.models.Movie
import com.example.pruebatecnicacargamos.navigation.AppScreens
import com.google.gson.Gson

@Composable
fun ImagesGrid(navController: NavController, listMovies: List<Movie>, lazyListState: LazyGridState){
    LazyVerticalGrid(columns = GridCells.Fixed(3), state = lazyListState) {
        items(listMovies){ movie ->
            AsyncImage(
                model = Constants.BASE_URL_IMAGES+movie.backdropPath,
                contentDescription = "Translated description of what the image contains",
                modifier = Modifier
                    .width(150.dp)
                    .height(190.dp)
                    .padding(3.dp)
                    .clickable {
                        val movieJson = Gson().toJson(movie)
                        navController.navigate(
                            "${AppScreens.DetailScreen.route}/${
                                Uri.encode(
                                    movieJson
                                )
                            }"
                        )
                    },
                contentScale = ContentScale.Crop
            )
        }
    }
}