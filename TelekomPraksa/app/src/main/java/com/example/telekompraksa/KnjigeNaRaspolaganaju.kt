package com.example.telekompraksa

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class KnjigeNaRaspolaganaju : AppCompatActivity() {
//    private val MAX_DESCRIPTION_LENGTH = 260
//    private var descriptionReadMoreShown = false
//    private var bookSaved = false
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
//        val zahtjev = intent.getParcelableExtra<KnjigaModel>("knjiga")
//        super.onCreate(savedInstanceState)
        setContentView(R.layout.prikaz_knjige_na_raspolaganju)
        val nazivKnjige: TextView = findViewById(R.id.nazivknjige)
        val imepisca: TextView = findViewById(R.id.imepisca)
        val desc: TextView = findViewById(R.id.desc)
        val brkomada: TextView = findViewById(R.id.brkomada)
        val imekategorije: TextView = findViewById(R.id.imekategorije)
        val nazivzanra: TextView = findViewById(R.id.nazivzanra)
        val imeautora: TextView = findViewById(R.id.imeautora)
        val imeizdavaca: TextView = findViewById(R.id.imeizdavaca)
        val godizdavanja: TextView = findViewById(R.id.godizdavanja)
        val rezervisanje: TextView = findViewById(R.id.reserve)

//        val gson = Gson()
//        val jsonbrat: String = gson.toJson(zahtjev)
//
//        val sharedPreferences = getSharedPreferences("moja brothers", MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//
//        editor.putString("cuvanje", jsonbrat)
//        editor.commit()


//        if (zahtjev != null) {
//            nazivKnjige.text = zahtjev.title
//
//            imepisca.text = zahtjev.authors[0].name
//            desc.text = zahtjev.summary
//            brkomada.text = if (zahtjev.quantity.toString().endsWith('1')
//                and !zahtjev.quantity.toString().endsWith("11")
//            ) {
//                "${zahtjev.quantity.toString()} komad"
//            } else {
//                "${zahtjev.quantity} komada"
//            }
//
//
//            imekategorije.text = zahtjev.categories[0].name
//            nazivzanra.text = zahtjev.genres[0].name
//            imeautora.text = zahtjev.authors[0].name
//            imeizdavaca.text = zahtjev.publisher
//            godizdavanja.text = zahtjev.publishYear
//
//
//        }
//        rezervisanje.setOnClickListener {
//            if (zahtjev?.available == false) {
//                Toast.makeText(
//                    this,
//                    "Svi primjerci su izdati, ne možete rezervisati!",
//                    Toast.LENGTH_LONG
//                ).show()
//            } else {
//                var url = "http://tim7.ictcortex.me/api/search-books?enable_pagination=false"
//                val request: StringRequest = object : StringRequest(
//                    Method.GET, url,
//                    Response.Listener { response ->
//                        if (response != null) {
//                            Log.i("Your Array Response", response)
//
//                            var res = Gson().fromJson(
//                                response,
//                                object : TypeToken<ArrayList<KnjigaModel>>() {}.type
//                            ) as ArrayList<KnjigaModel>
//                            Log.i("rezultat", res.toString())
//                            var reyclerView: RecyclerView = findViewById(R.id.odAutora)
//                            var adapteric: odAutoraAdapter = odAutoraAdapter(res, this)
//                            reyclerView.adapter = adapteric
//                            reyclerView.setHasFixedSize(true)
//                            reyclerView.setLayoutManager(GridLayoutManager(this, 2))
//                            adapteric.naKlik = {
//                                val intent = Intent(this, KnjigeNaRaspolaganaju::class.java)
//                                intent.putExtra("knjiga", it)
//                                startActivity(intent)
//                            }
//
//                        } else {
//                            Log.e("Your Array Response", "Data Null")
//                        }
//                    },
//                    Response.ErrorListener { error -> Log.e("error is ", error.toString()) }) {
//                    //This is for Headers If You Needed
//                    @Throws(AuthFailureError::class)
//                    override fun getHeaders(): Map<String, String> {
//                        val params: MutableMap<String, String> = HashMap()
//                        params["Content-Type"] = "application/json; charset=UTF-8"
//                        var tokenString = getSharedPreferences("MySharedPref", MODE_PRIVATE)
//                        var token: String = tokenString.getString("token", "").toString()
//                        params["Authorization"] = "Bearer $token"
//
//                        return params
//                    }
//
//
//                    override fun getParams(): Map<String, String>? {
//                        val params: MutableMap<String, String> = HashMap()
//                        params["enable_pagination"] = false.toString()
//
//
//                        return params
//                    }
//                }
//                val queue = Volley.newRequestQueue(applicationContext)
//                Log.i("link", request.headers.toString())
//                queue.add(request)
//                Log.i("Poslato", request.toString())
//            }
//        }
    }

}