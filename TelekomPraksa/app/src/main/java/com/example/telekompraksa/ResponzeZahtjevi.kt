package com.example.telekompraksa

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ResponzeZahtjevi(
    var id: String,
    var book: ZahtjevBook,
    var librarian: String,
    var dateFrom: String,
    var dateTo: String,
    var type: String
) : Parcelable {
    @Parcelize
    data class ZahtjevBook(
        var title: String,
        var authors: ArrayList<Autor>,
        var photo: String
    ) : Parcelable
}
