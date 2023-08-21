package com.example.pruebatecnicacargamos.navigation

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pruebatecnicacargamos.models.Movie
import com.example.pruebatecnicacargamos.screens.DetailScreen
import com.example.pruebatecnicacargamos.screens.MainScreen
import com.example.pruebatecnicacargamos.screens.FavoriteMoviesScreen
import com.google.gson.Gson

@Composable
fun AppNavigation(context: Context){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.MainScreen.route){
        composable(route = AppScreens.MainScreen.route){
            MainScreen(navController)
        }
        composable(
            route = "${AppScreens.DetailScreen.route}/{encodedMovie}",
            arguments = listOf(navArgument("encodedMovie") { type = NavType.StringType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val encodedMovie = arguments.getString("encodedMovie")
            val movie = Gson().fromJson(Uri.decode(encodedMovie), Movie::class.java)
            movie?.let { DetailScreen(navController, it, context) }
        }
        composable(route = AppScreens.FavoriteMoviesScreen.route){
            FavoriteMoviesScreen(navController, context)
        }
    }
}