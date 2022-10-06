package com.example.telekompraksa

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PaginiranaKategorija(
    val current_page:Int,
    val data: ArrayList<KategorijaModel>
): Parcelable{}
