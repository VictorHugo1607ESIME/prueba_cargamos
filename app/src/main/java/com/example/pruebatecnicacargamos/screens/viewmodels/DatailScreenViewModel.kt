package com.example.pruebatecnicacargamos.screens.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebatecnicacargamos.constants.Constants
import com.example.pruebatecnicacargamos.database.MovieDatabase
import com.example.pruebatecnicacargamos.database.getDatabase
import com.example.pruebatecnicacargamos.models.Movie
import com.example.pruebatecnicacargamos.models.MovieRoomState
import com.example.pruebatecnicacargamos.screens.repositorys.DetailScreenRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailScreenViewModel(private val context: Context, private val movie: Movie): ViewModel() {

    private val database: MovieDatabase = getDatabase(context)
    private val detailRepository = DetailScreenRepository(database)

    private val _status = MutableStateFlow(MovieRoomState())
    val status: StateFlow<MovieRoomState> = _status.asStateFlow()

    init {
        isExistMovie()
    }

    private fun isExistMovie(){
        viewModelScope.launch {
            try {
                val isExist = detailRepository.isExistMovie(movie)
                _status.value = _status.value.copy(isExistMovie = isExist, operation = Constants.IS_EXIST_MOVIE)
            }catch (e: Exception){
                Log.e("DetailScreenViewModel", e.toString())
            }
        }
    }

    fun insertMovie(){
        viewModelScope.launch {
            try {
                detailRepository.insertMovie(movie)
                _status.value = _status.value.copy(isExistMovie = true, operation = Constants.IS_EXIST_MOVIE)
            }catch (e: Exception){

            }
        }
    }

    fun deleteMovie(){
        viewModelScope.launch {
            try {
                detailRepository.deleteMovieFromFavorites(movie)
                _status.value = _status.value.copy(isExistMovie = false, operation = Constants.IS_EXIST_MOVIE)
            }catch (e: Exception){

            }
        }
    }

}