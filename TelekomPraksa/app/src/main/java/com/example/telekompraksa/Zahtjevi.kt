package com.example.telekompraksa

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue

import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley


class Zahtjevi : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        var requestQueue: RequestQueue = Volley.newRequestQueue(this)
        var url = "https://tim7.ictcortex.me/api/zahtjevi"

        var jar:JsonArrayRequest = object :
            JsonArrayRequest(Request.Method.GET,url,null,Response.Listener{ response ->


            }, Response.ErrorListener { error->
                Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()
            }){
            override fun getHeaders(): MutableMap<String, String>? {
                val params=HashMap<String,String>()
                var tokenString = getSharedPreferences("MySharedPref" ,  MODE_PRIVATE)
                var token:String = tokenString.getString("token","").toString()
                params.put("Authorization",token)

                return params
            }
        }
        requestQueue.add(jar)
        Log.i("Ajde","radi")
        setContentView(R.layout.moji_zahtjevi)
        Log.i("odgovor", "ae")
        }
    }
