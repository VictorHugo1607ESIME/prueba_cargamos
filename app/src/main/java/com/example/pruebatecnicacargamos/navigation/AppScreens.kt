package com.example.pruebatecnicacargamos.navigation

import com.example.pruebatecnicacargamos.constants.Constants

sealed class AppScreens(val route: String){

    object MainScreen: AppScreens(Constants.MAIN_SCREEN)
    object DetailScreen: AppScreens(Constants.DETAIL_SCREEN)
    object FavoriteMoviesScreen: AppScreens(Constants.FAVORITE_MOVIES_SCREEN)

}
