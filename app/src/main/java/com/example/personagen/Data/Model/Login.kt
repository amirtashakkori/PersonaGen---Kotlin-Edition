package com.example.personagen.Data.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Login (
    val username: String,
    val password: String
) : Parcelable