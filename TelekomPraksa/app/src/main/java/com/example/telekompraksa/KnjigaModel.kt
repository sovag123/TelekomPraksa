package com.example.telekompraksa

import android.content.Context
import android.os.Parcelable
import android.view.View
import kotlinx.android.parcel.Parcelize

@Parcelize
data class KnjigaModel(var id: Int,
                       var title: String,
                       var summary: String,
                       var authors: ArrayList<Autor>,
                       var categories: ArrayList<KategorijaModel>,
                       var genres: ArrayList<ZanrModel>,
                       var available: Boolean,
                       var quantity: Int,
                       var publisher: String,
                       var publishYear: String,
                       var photo: String): Parcelable, ModelCardController<KnjigaModel> {
    override fun bind(
        context: Context,
        itemView: View,
        onCardClick: (item: KnjigaModel) -> Unit,
        onAvailabilityIconClick: (available: Boolean) -> Unit
    ) {
//        val textTitle = itemView.findViewById<TextView>(R.id.card_book_text_title)
//        val textAuthor = itemView.findViewById<TextView>(R.id.card_book_text_author)
//        val img = itemView.findViewById<ImageView>(R.id.card_book_img)
//        val iconAvailability = itemView.findViewById<ImageView>(R.id.card_book_icon_availability)
//        val card = itemView.findViewById<MaterialCardView>(R.id.card_book_card)
//
//        card.useCompatPadding = true
//        textTitle.text = this.title
//
//        if (this.authors.isNotEmpty()) {
//            textAuthor.text = "by ${this.authors[0].name}"
//        } else {
//            textAuthor.text = ""
//        }
//
//        if (this.available) {
//            iconAvailability.setImageResource(R.drawable.ic_book_available)
//        } else {
//            iconAvailability.setImageResource(R.drawable.ic_book_unavailable)
//        }
//        iconAvailability.setOnClickListener { onAvailabilityIconClick(this.available) }
//
//        Glide.with(context)
//            .load(GlobalData.getImageUrl(this.photo))
//            .centerCrop()
//            .placeholder(R.color.black)
//            .into(img)
//
//        card.setOnClickListener { onCardClick(this) }
    }
}
