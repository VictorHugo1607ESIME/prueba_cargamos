package com.example.pruebatecnicacargamos.screens.repositorys

import com.example.pruebatecnicacargamos.constants.Constants
import com.example.pruebatecnicacargamos.models.ResponseListMoviesState
import com.example.pruebatecnicacargamos.models.ResponseMovies
import com.example.pruebatecnicacargamos.services.CallServices
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MainScreenRepository {

    private val _response = MutableStateFlow(ResponseListMoviesState())
    val response: StateFlow<ResponseListMoviesState> = _response.asStateFlow()

    suspend fun getListMovies(page: Int): ResponseListMoviesState {
        return suspendCoroutine { continuation ->
            CallServices.callServiceApp().getListMovies(Constants.TOKEN, page).enqueue(object :
                Callback<ResponseMovies> {
                override fun onResponse(
                    call: Call<ResponseMovies>,
                    response: Response<ResponseMovies>
                ) {
                    if (response.code() == 200) {
                        val responseData = response.body()
                        val listMovieStatus = ResponseListMoviesState(dataResponse = responseData as ResponseMovies, status = Constants.SUCCESS)
                        continuation.resume(listMovieStatus)
                    } else {
                        val listMovieStatus = ResponseListMoviesState(status = Constants.FAIL, message = response.message())
                        continuation.resume(listMovieStatus)
                    }
                }

                override fun onFailure(call: Call<ResponseMovies>, t: Throwable) {
                    val listMovieStatus = ResponseListMoviesState(status = Constants.FAIL, message = t.message.toString())
                    continuation.resume(listMovieStatus)
                }
            })
        }
    }
}