package com.example.pruebatecnicacargamos.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pruebatecnicacargamos.constants.Constants
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = Constants.MOVIES)
data class Movie(

    @PrimaryKey val id: Int       = 0,
    val adult         : Boolean   = false,
    val overview      : String    = "",
    val popularity    : Double    = 0.0,
    val title         : String    = "",
    val video         : Boolean   = false,
    @ColumnInfo(name = "backdrop_path"      ) @SerializedName("backdrop_path"     ) val backdropPath      : String    = "",
    @ColumnInfo(name = "genre_ids"          ) @SerializedName("genre_ids"         ) val genreIds          : List<Int> = emptyList(),
    @ColumnInfo(name = "original_language"  ) @SerializedName("original_language" ) val originalLanguage  : String    = "",
    @ColumnInfo(name = "original_title"     ) @SerializedName("original_title"    ) val originalTitle     : String    = "",
    @ColumnInfo(name = "poster_path"        ) @SerializedName("poster_path"       ) val posterPath        : String    = "",
    @ColumnInfo(name = "release_date"       ) @SerializedName("release_date"      ) val releaseDate       : String    = "",
    @ColumnInfo(name = "vote_average"       ) @SerializedName("vote_average"      ) val voteAverage       : Double    = 0.0,
    @ColumnInfo(name = "vote_count"         ) @SerializedName("vote_count"        ) val voteCount         : Int       = 0

): Serializable