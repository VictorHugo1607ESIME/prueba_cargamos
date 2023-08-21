package com.example.pruebatecnicacargamos.screens.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebatecnicacargamos.constants.Constants
import com.example.pruebatecnicacargamos.models.Movie
import com.example.pruebatecnicacargamos.models.ResponseListMoviesState
import com.example.pruebatecnicacargamos.screens.repositorys.MainScreenRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainScreenViewModel(): ViewModel() {

    private val mainRepository = MainScreenRepository()
    private var page = 0
    private var allListMovies: ArrayList<Movie> = ArrayList()

    private val _responseState = MutableStateFlow(ResponseListMoviesState())
    val responseState: StateFlow<ResponseListMoviesState> = _responseState.asStateFlow()

    fun getListMovies(){
        viewModelScope.launch {
            try {
                mainRepository.response.value.status = Constants.LOAD
                page++
                val response = mainRepository.getListMovies(page)
                allListMovies.addAll(response.dataResponse.results)
                response.dataResponse.results = allListMovies
                _responseState.value = response
            }catch (e: Exception){

            }
        }
    }

}