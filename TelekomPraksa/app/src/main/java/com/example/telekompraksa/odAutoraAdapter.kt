package com.example.telekompraksa

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import androidx.core.content.ContextCompat

class odAutoraAdapter(private val mList: List<KnjigaModel>,val context: Context) :
    RecyclerView.Adapter<odAutoraAdapter.ViewHolder>() {
    var naKlik:((KnjigaModel)->Unit)?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_od_autora, parent, false)

        return ViewHolder(view)

    }


    override fun onBindViewHolder(holder: odAutoraAdapter.ViewHolder, position: Int) {
        val trenutni = mList[position]
        val slikica = "https://tim7.ictcortex.me/storage/image/" + trenutni.photo.toString()
        Glide.with(context).load(slikica).centerCrop().placeholder(R.drawable.ic_cortex)
            .into(holder.imageView)
        holder.naslovK.text=trenutni.title
        if(trenutni.authors!=null) {
            holder.pisacK.text = "by " + trenutni.authors[0].name
        }
        if(trenutni.available){
            holder.imageViewS.setImageResource(R.drawable.ic_tl_srick)
        }
        else{
            holder.imageViewS.setImageResource(R.drawable.ic_tl_x)
        }
        holder.itemView.setOnClickListener {
            naKlik?.invoke(trenutni)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.prikazK)
        val imageViewS: ImageView = itemView.findViewById(R.id.strikK)
        val naslovK: TextView = itemView.findViewById(R.id.naslovK)
        val pisacK: TextView = itemView.findViewById(R.id.pisacK)

    }



}