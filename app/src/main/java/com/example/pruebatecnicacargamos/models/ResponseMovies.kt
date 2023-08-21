package com.example.pruebatecnicacargamos.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseMovies(

    @SerializedName("page"          ) val page          : Int           = 0,
    @SerializedName("results"       ) var results       : ArrayList<Movie>   = ArrayList(),
    @SerializedName("total_pages"   ) val totalPages    : Int           = 0,
    @SerializedName("total_results" ) val totalResults  : Int           = 0

):Serializable