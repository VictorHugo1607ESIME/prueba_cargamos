package com.example.pruebatecnicacargamos.models

import com.example.pruebatecnicacargamos.constants.Constants

data class MovieRoomState(

    var isExistMovie: Boolean = false,
    var message: String = Constants.STRING_EMPTY,
    var operation: String = Constants.STRING_EMPTY

)