package com.example.telekompraksa


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Autor(
    val id: Int,

    val name: String,
    val biography: String?,
) : Parcelable, FilterModel {
    override fun getChipText(): String {
        return name
    }

    override fun getChipId(): Int {
        return id
    }
}
