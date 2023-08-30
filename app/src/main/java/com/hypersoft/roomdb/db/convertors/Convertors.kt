package com.hypersoft.roomdb.db.convertors

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.hypersoft.roomdb.db.tables.City

class Convertors {

    @TypeConverter
    fun fromCity(city: City): String {
        return Gson().toJson(city)
    }

    @TypeConverter
    fun toCity(json: String): City {
        return Gson().fromJson(json, City::class.java)
    }
}