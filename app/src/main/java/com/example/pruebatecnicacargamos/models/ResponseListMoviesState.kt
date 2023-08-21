package com.example.pruebatecnicacargamos.models

import com.example.pruebatecnicacargamos.constants.Constants
import java.io.Serializable

data class ResponseListMoviesState(

    var dataResponse: ResponseMovies = ResponseMovies(),
    var status: Int = Constants.LOAD,
    var message: String = Constants.STRING_EMPTY

):Serializable