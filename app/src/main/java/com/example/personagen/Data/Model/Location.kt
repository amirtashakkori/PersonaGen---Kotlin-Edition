package com.example.personagen.Data.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location (
    val country: String,
    val state: String,
    val city: String,
    val street: Street,
    val postcode: String
) : Parcelable