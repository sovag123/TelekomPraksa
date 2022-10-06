package com.example.telekompraksa

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton

class zahtjevi_adapter(private val mList: List<ResponzeZahtjevi>, val context: Context) :
    RecyclerView.Adapter<zahtjevi_adapter.ViewHolder>() {
    var naKlik:((ResponzeZahtjevi)->Unit)?=null
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.zahtjev_view_row, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val trenutni = mList[position]
        val slikica = "https://tim7.ictcortex.me/storage/image/" + trenutni.book.photo.toString()
        Glide.with(context).load(slikica).centerCrop().placeholder(R.drawable.ic_cortex)
            .into(holder.imageView)
        holder.autor.setText("by "+trenutni.book.authors[0].name)
        holder.ime.setText(trenutni.book.title)
        if (trenutni.dateTo == "") {
            holder.datumDo.setTextColor(ContextCompat.getColor(context, R.color.crvena))
            holder.datumDo.setText(" Nije vraćena")
        } else {

            holder.datumDo.text = trenutni.dateTo.substring(0, 10)
        }
        holder.datum.setText("Period rezervacje: " + trenutni.dateFrom.substring(0, 10)+ " - ")
        holder.tip.setText("Tip zahtjeva")
        when (trenutni.type) {
            "reservation rejected" -> {
                val color = ContextCompat.getColor(context, R.color.crvena)
                holder.dugme.strokeColor = ColorStateList.valueOf(color)
                holder.dugme.setTextColor(color)

                holder.dugme.setText("Odbijeno")
            }
            "reservation" -> {
                val color = ContextCompat.getColor(context, R.color.narand)
                holder.dugme.strokeColor = ColorStateList.valueOf(color)
                holder.dugme.setTextColor(color)

                holder.dugme.setText("Rezervacija")
            }
            "rent" -> {
                val color = ContextCompat.getColor(context, R.color.zelena)
                holder.dugme.strokeColor = ColorStateList.valueOf(color)
                holder.dugme.setTextColor(color)

                holder.dugme.setText("Zaduživanje")
            }
        }
        holder.itemView.setOnClickListener {
        naKlik?.invoke(trenutni)
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.slikaZah)
        val autor: TextView = itemView.findViewById(R.id.autor)
        val ime: TextView = itemView.findViewById(R.id.naslovZah)
        val datum: TextView = itemView.findViewById(R.id.periodZah)
        val datumDo: TextView = itemView.findViewById(R.id.autorZahDo)
        val tip: TextView = itemView.findViewById(R.id.tipZah)
        val dugme: MaterialButton = itemView.findViewById(R.id.dugmeZah)
    }
}