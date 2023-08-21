package com.example.pruebatecnicacargamos.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.pruebatecnicacargamos.models.Movie

@Dao
interface MovieDAO {
    @Insert
    fun insertMovie(movie: Movie)

    @Query("SELECT * FROM movies")
    fun getListFavoriteMovies(): MutableList<Movie>

    @Delete
    fun deleteFavoriteMovie(movie: Movie)

    @Query("SELECT EXISTS (SELECT 1 FROM movies WHERE id = :movieId)")
    fun isMovieExists(movieId: Int): Boolean
}