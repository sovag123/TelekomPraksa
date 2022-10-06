package com.example.telekompraksa.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Knjiga(
    var title: String,
    var authors: ArrayList<String>,
    var photo: String,
    var available: Boolean
) : Parcelable
