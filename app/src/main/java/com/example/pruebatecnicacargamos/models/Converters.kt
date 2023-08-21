package com.example.pruebatecnicacargamos.models

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromListInt(list: List<Int>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun toListInt(value: String): List<Int> {
        return value.split(",").map { it.toInt() }
    }
}