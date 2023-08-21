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

class FavoriteMoviesViewModel(private val context: Context): ViewModel() {

    private val database: MovieDatabase = getDatabase(context)
    private val detailRepository = DetailScreenRepository(database)

    private val _status = MutableStateFlow(ArrayList<Movie>())
    val status: StateFlow<ArrayList<Movie>> = _status.asStateFlow()

    init {
        getListMovies()
    }

    private fun getListMovies(){
        viewModelScope.launch {
            try {
                val listMovies = detailRepository.getListMovies()
                _status.value.addAll(listMovies as ArrayList<Movie>)
            }catch (e: Exception){
                Log.e("DetailScreenViewModel", e.toString())
            }
        }
    }
}