package com.example.telekompraksa

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.google.gson.Gson

class NalogActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        var url = "https://tim7.ictcortex.me/api/user"
        setContentView(R.layout.nalog_main_layout)
        super.onCreate(savedInstanceState)
        var slika:ImageView = findViewById(R.id.slikaProf)
        var imeP:TextView=findViewById(R.id.ImePrez)
        var jmbg:TextView=findViewById(R.id.jmbg)
        var emailP:TextView=findViewById(R.id.emailP)
        var imePa:TextView=findViewById(R.id.imeP)
        var editDugme:ImageButton=findViewById(R.id.edit)



        val request: StringRequest = object : StringRequest(
            Method.GET, url,
            Response.Listener { response ->
                if (response != null) {
                    Log.i("Your Array Response", response)
                    var res = Gson().fromJson(
                        response,
                        User::class.java
                    ) as User

                    val slikica = "https://tim7.ictcortex.me/storage/image/" + res.photo
                    if(res.photo!="")
                    Glide.with(this).load(slikica).centerCrop().placeholder(R.drawable.ic_cortex)
                        .into(slika)
                    imeP.text=res.name
                    jmbg.text=res.jmbg
                    emailP.text=res.email
                    imePa.text=res.username
                    editDugme.setOnClickListener(){
                        val intent = Intent(this,edit_user::class.java)
                        intent.putExtra("profil",res)
                        startActivity(intent)
                    }

                } else {
                    Log.e("Your Array Response", "Data Null")
                }
            },
            Response.ErrorListener { error -> Log.e("error is ", error.toString()) }) {
            //This is for Headers If You Needed
//            @Throws(AuthFailureError::class)
//            override fun getHeaders(): Map<String, String> {
//                val params: MutableMap<String, String> = HashMap()
//                params["Content-Type"] = "application/json; charset=UTF-8"
//                var tokenString = getSharedPreferences("MySharedPref", MODE_PRIVATE)
//                var token: String = tokenString.getString("token", "").toString()
//                params["Authorization"] = "Bearer $token"
//                Log.i("auto je", params["Authorization"].toString())
//                return params
//            }

            //Pass Your Parameters here
//            override fun getParams(): Map<String, String>? {
//                val params: MutableMap<String, String> = HashMap()
//                params["User"] = UserName
//                params["Pass"] = PassWord
//                return params
//            }
        }
        val queue = Volley.newRequestQueue(applicationContext)
        queue.add(request)
        Log.i("Poslato", request.toString())
    }

}