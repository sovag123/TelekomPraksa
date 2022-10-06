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

class AktivnostiAdapter(private val mList: List<Aktivnost>,val context: Context): RecyclerView.Adapter<AktivnostiAdapter.ViewHolder>(){
    var naKlik:((Aktivnost)->Unit)?=null
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.aktivnost_view_row, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val trenutni = mList[position]
        val slikica = "https://tim7.ictcortex.me/storage/image/" + trenutni.photo.toString()
        Glide.with(context).load(slikica).centerCrop().placeholder(R.drawable.ic_cortex)
            .into(holder.imageView)
        when (trenutni.type) {
            "reservation" -> holder.izdavanje.setText(R.string.rezervacija_knjige)
            "rent" -> holder.izdavanje.setText(R.string.izdavanje_knjige)
        }
        holder.izdavac.text=trenutni.librarian
        holder.knjigaa.text=trenutni.book

        holder.datum.text=trenutni.date.substring(0, 10)
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
        val imageView: ImageView = itemView.findViewById(R.id.circleImageView)
        val izdavanje: TextView = itemView.findViewById(R.id.textView)
        val izdavac: TextView = itemView.findViewById(R.id.textView11)
        val knjigaa: TextView = itemView.findViewById(R.id.textView19)
        val datum: TextView = itemView.findViewById(R.id.textView20)

    }
}