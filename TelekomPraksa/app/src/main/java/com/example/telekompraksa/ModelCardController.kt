package com.example.telekompraksa

import android.content.Context
import android.view.View

interface ModelCardController <T> {
    fun bind(context: Context,
             itemView: View,
             onCardClick: (item: T) -> Unit,
             onAvailabilityIconClick: (available: Boolean) -> Unit
    )
}