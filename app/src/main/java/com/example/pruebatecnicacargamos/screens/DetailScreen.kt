package com.example.pruebatecnicacargamos.screens

import android.content.Context
import android.net.Uri
import android.os.Parcelable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pruebatecnicacargamos.R
import com.example.pruebatecnicacargamos.constants.Constants
import com.example.pruebatecnicacargamos.models.Movie
import com.example.pruebatecnicacargamos.navigation.AppScreens
import com.example.pruebatecnicacargamos.screens.viewmodels.DetailScreenViewModel
import com.google.gson.Gson

@Composable
fun DetailScreen(navController: NavController, movie: Movie, context: Context) {

    val detailViewModel = DetailScreenViewModel(context, movie)
    val detailStatus = detailViewModel.status.collectAsState()

    when(detailStatus.value.operation){

        Constants.IS_EXIST_MOVIE ->{
            DetailContent(navController, movie, detailStatus.value.isExistMovie, detailViewModel)
        }

    }

}

@Composable
fun DetailContent(navController: NavController, movie: Movie, isExistMovie: Boolean, detailViewModel: DetailScreenViewModel){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.Black),
            modifier = Modifier
                .padding(5.dp)
                .width(30.dp)
                .height(30.dp)
                .clickable {
                    navController.popBackStack()
                }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = Constants.BASE_URL_IMAGES+movie.backdropPath,
                contentDescription = "Translated description of what the image contains",
                modifier = Modifier
                    .width(150.dp)
                    .height(200.dp)
                    .padding(3.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    text = movie.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
                Text(
                    text = movie.overview,
                    fontSize = 10.sp,
                    lineHeight = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
                Row(
                    modifier = Modifier.wrapContentHeight()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_star_24),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.Red)
                    )
                    Text(text = movie.voteAverage.toString())
                }
            }
        }
        
        Button(
            onClick = {
                if(!isExistMovie)
                    detailViewModel.insertMovie()
                else
                    detailViewModel.deleteMovie()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            if (!isExistMovie)
                Text(text = "Agregar a favoritos")
            else
                Text(text = "Eliminar de favoritos")
        }
    }
    
}