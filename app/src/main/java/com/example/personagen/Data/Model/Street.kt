package com.example.personagen.Data.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Street (
    val number: Int,
    val name : String
) : Parcelable