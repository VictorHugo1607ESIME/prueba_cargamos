package com.example.pruebatecnicacargamos.screens

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pruebatecnicacargamos.constants.Constants
import com.example.pruebatecnicacargamos.models.Movie
import com.example.pruebatecnicacargamos.navigation.AppScreens
import com.example.pruebatecnicacargamos.screens.viewmodels.MainScreenViewModel
import com.example.pruebatecnicacargamos.services.interfaces.Movies
import com.google.gson.Gson

@Composable
fun MainScreen(navController: NavController){
    val mainViewModel = viewModel<MainScreenViewModel>()
    val responseState by mainViewModel.responseState.collectAsState()
    val lazyListState = rememberLazyGridState()
    var hasReachedEnd by remember { mutableStateOf(false) }
    var listMovies = ArrayList<Movie>()

    // Llamar getListMovies solo si no se ha llamado previamente y el estado está en LOAD
    if (responseState.status == Constants.LOAD) {
        mainViewModel.getListMovies()
    }

    val isScrolledToEnd = lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == responseState.dataResponse.results.size - 1

    when {
        responseState.status == Constants.SUCCESS -> {
            listMovies.addAll(responseState.dataResponse.results)

            // Si ya ha alcanzado el final del scroll, restablece hasReachedEnd a false
            if (isScrolledToEnd && hasReachedEnd) {
                hasReachedEnd = false
            }

            BodyContent(navController, listMovies, lazyListState)
            if (isScrolledToEnd && !hasReachedEnd) {
                hasReachedEnd = true
                mainViewModel.getListMovies()
                Log.d("Scroll", "Llegaste al final del scroll")
            }
        }
        responseState.status == Constants.FAIL -> {
            Log.d("MainScreen", responseState.message)
            // Mostrar un mensaje de error o algún indicador visual de fallo
        }
    }
}

@Composable
fun BodyContent(navController: NavController, listMovies: List<Movie>, lazyListState: LazyGridState){

    ImagesGrid(navController = navController, listMovies = listMovies, lazyListState = lazyListState)

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(5.dp),
            onClick = {
                navController.navigate(AppScreens.FavoriteMoviesScreen.route)
            }
        ) {
            Text("Ir a favoritos")
        }
    }
}