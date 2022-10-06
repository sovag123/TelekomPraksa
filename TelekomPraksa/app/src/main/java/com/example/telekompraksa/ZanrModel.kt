package com.example.telekompraksa

import android.content.Context
import android.os.Parcelable
import android.view.View
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ZanrModel(
    val id: Int,

    val name: String,
    val photo: String,
    val description: String?,
) : Parcelable, FilterModelController, ModelCardController<ZanrModel> {
    override fun getChipText(): String {
        return name
    }

    override fun getChipId(): Int {
        return id
    }

    override fun bind(
        context: Context,
        itemView: View,
        onCardClick: (item: ZanrModel) -> Unit,
        onAvailabilityIconClick: (available: Boolean) -> Unit
    ) {
//        val textTitle = itemView.findViewById<TextView>(R.id.card_category_genre_text_title)
//        val img = itemView.findViewById<ImageView>(R.id.card_category_genre_img)
//        val card = itemView.findViewById<MaterialCardView>(R.id.card_category_genre_card)
//
//        card.useCompatPadding = true
//        textTitle.text = this.name
//
//        Glide.with(context)
//            .load(GlobalData.getImageUrl(this.photo))
//            .centerCrop()
//            .placeholder(R.drawable.placeholder_image)
//            .into(img)
//
//        card.setOnClickListener { onCardClick(this) }
    }
}
