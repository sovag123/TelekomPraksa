package com.example.telekompraksa
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseLogin(val msg: String, val plainTextToken: String) : Parcelable {

}

