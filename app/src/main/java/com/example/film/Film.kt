package com.example.film

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    val name: String,
    val description: String,
    val photo: Int,
    val keterangan: String  // Tambahkan ini
) : Parcelable