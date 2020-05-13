package com.example.mobdevass3.utils

import androidx.room.TypeConverter
import com.example.mobdevass3.domain.entity.Colors
import com.example.mobdevass3.domain.entity.Photos
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters{

    @TypeConverter
    fun fromStringToArraylist(value: String): ArrayList<String>{
        val mapType = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun fromArraylistToString(list: ArrayList<String>): String{
        val gson = Gson()
        return gson.toJson(list)
    }


    @TypeConverter
    fun fromPhotosToString(photos:ArrayList<Photos>):String{
        val gson = Gson()
        return gson.toJson(photos)
    }

    @TypeConverter
    fun fromStringToPhotos(photos:String):ArrayList<Photos>{
        val mapType = object : TypeToken< ArrayList<Photos>>() {}.type
        return Gson().fromJson(photos, mapType)
    }


    @TypeConverter
    fun fromColorsToString(colors: Colors): String {
        val gson = Gson()
        return gson.toJson(colors)
    }

    @TypeConverter
    fun fromStringToColors(colors:String): Colors{
        val mapType = object : TypeToken<Colors>() {}.type
        return Gson().fromJson(colors, mapType)
    }

}