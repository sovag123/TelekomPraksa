package com.example.telekompraksa

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var jmbg: String,
    var name: String,
    var email: String,
    var username: String,
    var photo: String,
) : Parcelable
