package com.example.pruebatecnicacargamos.services

import com.example.pruebatecnicacargamos.services.interfaces.Movies

object CallServices {

    fun callServiceApp() = RetrofitClient.retrofitApp().create(
        Movies::class.java
    )
}