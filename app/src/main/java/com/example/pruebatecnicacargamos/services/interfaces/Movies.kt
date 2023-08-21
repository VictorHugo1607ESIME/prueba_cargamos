package com.example.pruebatecnicacargamos.services.interfaces

import com.example.pruebatecnicacargamos.constants.Constants
import com.example.pruebatecnicacargamos.models.ResponseMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface Movies {

    @GET(Constants.GET_LIST_PAGE_MOVIES)
    fun getListMovies(
        @Header("Authorization") authHeader : String,
        @Query("page") page : Int
    ):Call<ResponseMovies>

}