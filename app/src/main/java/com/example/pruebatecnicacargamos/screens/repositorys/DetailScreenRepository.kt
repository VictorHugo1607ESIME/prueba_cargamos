package com.example.pruebatecnicacargamos.screens.repositorys

import android.content.Context
import com.example.pruebatecnicacargamos.database.MovieDatabase
import com.example.pruebatecnicacargamos.database.getDatabase
import com.example.pruebatecnicacargamos.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailScreenRepository(private val database: MovieDatabase) {


    suspend fun insertMovie(movie: Movie){
        return withContext(Dispatchers.IO) {
            database.movieDAO.insertMovie(movie)
        }
    }

    suspend fun deleteMovieFromFavorites(movie: Movie){
        return withContext(Dispatchers.IO) {
            database.movieDAO.deleteFavoriteMovie(movie)
        }
    }

    suspend fun isExistMovie(movie: Movie): Boolean{
        return withContext(Dispatchers.IO) {
            database.movieDAO.isMovieExists(movie.id)
        }
    }

    suspend fun getListMovies(): ArrayList<Movie>{
        return withContext(Dispatchers.IO) {
            database.movieDAO.getListFavoriteMovies() as ArrayList<Movie>
        }
    }
}