package com.example.personagen.Data.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "table_users")
data class User (
    @PrimaryKey(autoGenerate = true) @Expose(deserialize = false)
    val userId: Long = 0,
    val gender: String,
    val name: Name,
    val location: Location,
    val email: String,
    val login: Login,
    val dob: Dob,
    val registered: Registered,
    val phone: String,
    val cell: String,
    val picture: Picture,
    val nat: String

) : Parcelable