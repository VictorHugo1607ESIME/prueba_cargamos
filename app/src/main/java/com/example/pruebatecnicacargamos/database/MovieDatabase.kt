package com.example.pruebatecnicacargamos.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pruebatecnicacargamos.constants.Constants
import com.example.pruebatecnicacargamos.models.Converters
import com.example.pruebatecnicacargamos.models.Movie

@Database(entities = [Movie::class], version = Constants.VERSION_DB)
@TypeConverters(Converters::class)
abstract class MovieDatabase: RoomDatabase() {
    abstract val movieDAO: MovieDAO
}

private lateinit var INSTANCE: MovieDatabase

fun getDatabase(context: Context): MovieDatabase {
    synchronized(MovieDatabase::class.java){
        if(!::INSTANCE.isInitialized){
            INSTANCE = Room
                .databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie_db"
                    ).build()
        }
        return  INSTANCE
    }
}