package com.example.telekompraksa

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class KategorijaAdapter (private val mList: List<KategorijaModel>,val context: Context) :
    RecyclerView.Adapter<KategorijaAdapter.ViewHolder>() {
    var naKlik:((KategorijaModel)->Unit)?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.kategorija_view_row, parent, false)

        return ViewHolder(view)

    }


    override fun onBindViewHolder(holder: KategorijaAdapter.ViewHolder, position: Int) {
        val trenutni = mList[position]
        val slikica = "https://tim7.ictcortex.me/storage/image/" + trenutni.photo.toString()
        Glide.with(context).load(slikica).centerCrop().placeholder(R.drawable.ic_cortex)
            .into(holder.imageView)
        holder.pisacK.text=trenutni.name
        holder.itemView.setOnClickListener {
            naKlik?.invoke(trenutni)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.prikazK)
        val pisacK: TextView = itemView.findViewById(R.id.naslovK)

    }
}