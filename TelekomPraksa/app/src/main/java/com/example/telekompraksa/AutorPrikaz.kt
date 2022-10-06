package com.example.telekompraksa

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import layout.Paginated


class AutorPrikaz: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autor_prikaz)
        val zahtjev = intent.getParcelableExtra<Autor>("pisac")
        val imageView: ImageView = findViewById(R.id.slikapisca)
        val ime: TextView = findViewById(R.id.imePisca)
        val oPiscu: TextView = findViewById(R.id.oPiscu)
        val ponovo: TextView = findViewById(R.id.pisacPonovo)
        val slikica = "https://tim7.ictcortex.me/storage/image/" + zahtjev.toString()
        Glide.with(this).load(slikica).centerCrop().placeholder(R.drawable.ic_cortex)
            .into(imageView)
        ime.text=zahtjev?.name
        oPiscu.text=zahtjev?.biography
        ponovo.text=zahtjev?.name
        var url = "http://tim7.ictcortex.me/api/search-books?enable_pagination=false"
        val request: StringRequest = object : StringRequest(
            Method.GET, url,
            Response.Listener { response ->
                if (response != null) {
                    Log.i("Your Array Response", response)

                    var res = Gson().fromJson(
                        response,
                        object : TypeToken<ArrayList<KnjigaModel>>() {}.type
                    ) as ArrayList<KnjigaModel>
                    Log.i("rezultat",res.toString())
                    var reyclerView: RecyclerView = findViewById(R.id.odAutora)
                    var adapteric: odAutoraAdapter = odAutoraAdapter(res, this)
                    reyclerView.adapter = adapteric

                    reyclerView.setHasFixedSize(true)
                    reyclerView.setLayoutManager(GridLayoutManager(this, 2))


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
                return params
            }


            override fun getParams(): Map<String, String>? {
                val params: MutableMap<String, String> = HashMap()
                params["enable_pagination"] = false.toString()
                params["authors[]"] = zahtjev?.id.toString()

                return params
            }
        }
        val queue = Volley.newRequestQueue(applicationContext)
        Log.i("link",request.headers.toString())
        queue.add(request)
        Log.i("Poslato", request.toString())
    }
}