package com.example.telekompraksa

import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ZahtjevDetalji : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zahtjev_detalji)
        val zahtjev = intent.getParcelableExtra<ResponzeZahtjevi>("zahtjev")
        if(zahtjev!=null){
            val imageView: ImageView = findViewById(R.id.slikaZahPrav)
            val autor: TextView = findViewById(R.id.obican2)
            val datum: TextView = findViewById(R.id.datumZO)
            val datumDo: TextView = findViewById(R.id.datumZD)
            val izdavac: TextView = findViewById(R.id.izdavacZ)
            val broj: TextView = findViewById(R.id.brojZ)
            val dugme: MaterialButton = findViewById(R.id.dugmePZah)
            val dugmep:TextView = findViewById(R.id.ponistR)


            dugmep.setOnClickListener(){
                Log.i("Your Array Response", "Data Null")



                var url = "https://tim7.ictcortex.me/api/izbrisi-transakciju"



                val request: StringRequest = object : StringRequest(
                    Method.DELETE, url,
                    Response.Listener { response ->
                        if (response != null) {
                            Log.i("radddddSASDASD", response)

                                val intent = Intent(this,mojipravizahtjevi::class.java)
                                intent.putExtra("zahtjev","izbrisano")
                                startActivity(intent)


                        } else {
                            Log.e("Your Array Response", "Data Null")
                        }
                    },
                    Response.ErrorListener { error -> Log.e("error is ", error.toString()) }) {
                    //This is for Headers If You Needed
                    @Throws(AuthFailureError::class)
                    override fun getHeaders(): Map<String, String> {
                        val params: MutableMap<String, String> = HashMap()
                        params["Content-Type"] = "application/json; charset=UTF-8"
                        var tokenString = getSharedPreferences("MySharedPref", MODE_PRIVATE)
                        var token: String = tokenString.getString("token", "").toString()
                        params["Authorization"] = "Bearer $token"
                        Log.i("auto je", params["Authorization"].toString())
                        return params
                    }


            override fun getParams(): Map<String, String>? {
//                if(zahtjev.type=="reservation")
                    val params: MutableMap<String, String> = HashMap()
                    params["id"] = zahtjev.id
                    params["type"] = zahtjev.type
                    return params


            }
                }


                val queue = Volley.newRequestQueue(applicationContext)
                queue.add(request)



            }
            val slikica = "https://tim7.ictcortex.me/storage/image/" + zahtjev.book.photo.toString()
            Glide.with(this).load(slikica).centerCrop().placeholder(R.drawable.ic_cortex)
                .into(imageView)
            autor.text = zahtjev.book.authors[0].name
            broj.text = zahtjev.id
            autor.setOnClickListener(){
                val intent = Intent(this,AutorPrikaz::class.java)
                intent.putExtra("pisac",zahtjev.book.authors[0])
                startActivity(intent)
            }
            datum.setText(zahtjev.dateFrom.substring(0, 10))
            if (zahtjev.dateTo == "") {
                datumDo.setTextColor(ContextCompat.getColor(this, R.color.crvena))
                datumDo.setText(" Nije vraćena")
            } else {

                datumDo.text = zahtjev.dateTo.substring(0, 10)
            }
            izdavac.setText(zahtjev.librarian)
//            broj.setText(zahtjev.)
            if(zahtjev.type=="reservation"){
                dugmep.text ="Poništi rezervaciju"
            }
            else{
                dugmep.text="Izbriši Transakciju"
            }

            when (zahtjev.type) {
                "reservation rejected" -> {
                    val color = ContextCompat.getColor(this, R.color.crvena)
                    dugme.strokeColor = ColorStateList.valueOf(color)
                    dugme.setTextColor(color)

                    dugme.setText("Odbijeno")
                }
                "reservation" -> {
                    val color = ContextCompat.getColor(this, R.color.narand)
                    dugme.strokeColor = ColorStateList.valueOf(color)
                    dugme.setTextColor(color)

                    dugme.setText("Rezervacija")
                }
                "rent" -> {
                    val color = ContextCompat.getColor(this, R.color.zelena)
                    dugme.strokeColor = ColorStateList.valueOf(color)
                    dugme.setTextColor(color)

                    dugme.setText("Zaduživanje")
                }
            }
        }
    }
}