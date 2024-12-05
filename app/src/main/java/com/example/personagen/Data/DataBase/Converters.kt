package com.example.personagen.Data.DataBase

import androidx.room.TypeConverter
import com.example.personagen.Data.Model.Dob
import com.example.personagen.Data.Model.Location
import com.example.personagen.Data.Model.Login
import com.example.personagen.Data.Model.Name
import com.example.personagen.Data.Model.Picture
import com.example.personagen.Data.Model.Registered
import com.google.gson.Gson

object Converters {
    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun fromStringToName(value: String): Name = gson.fromJson(value, Name::class.java)

    @TypeConverter
    @JvmStatic
    fun fromNameToString(name: Name): String = gson.toJson(name)

    @TypeConverter
    @JvmStatic
    fun fromStringToLocation(value: String): Location = gson.fromJson(value, Location::class.java)

    @TypeConverter
    @JvmStatic
    fun fromLocationToString(location: Location): String = gson.toJson(location)

    @TypeConverter
    @JvmStatic
    fun fromStringToLogin(value: String): Login = gson.fromJson(value, Login::class.java)

    @TypeConverter
    @JvmStatic
    fun fromLoginToString(login: Login): String = gson.toJson(login)

    @TypeConverter
    @JvmStatic
    fun fromStringToDob(value: String): Dob = gson.fromJson(value, Dob::class.java)

    @TypeConverter
    @JvmStatic
    fun fromDobToString(dob: Dob): String = gson.toJson(dob)

    @TypeConverter
    @JvmStatic
    fun fromStringToRegistered(value: String): Registered = gson.fromJson(value, Registered::class.java)

    @TypeConverter
    @JvmStatic
    fun fromRegisteredToString(registered: Registered): String = gson.toJson(registered)

    @TypeConverter
    @JvmStatic
    fun fromStringToPicture(value: String): Picture = gson.fromJson(value, Picture::class.java)

    @TypeConverter
    @JvmStatic
    fun fromPictureToString(picture: Picture): String = gson.toJson(picture)
}
