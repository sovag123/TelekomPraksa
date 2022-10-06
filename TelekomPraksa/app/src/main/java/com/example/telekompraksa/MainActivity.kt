package com.example.telekompraksa

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import org.json.JSONObject
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.login_main_layout)
        val txtUsername = findViewById<TextInputEditText>(R.id.user)
        val txtPassword = findViewById<TextInputEditText>(R.id.pass)
        val btnLogin = findViewById<Button>(R.id.login_btn_log_in)

        btnLogin.setOnClickListener {
            val username:String = txtUsername.text.toString()
            val password:String = txtPassword.text.toString()

            loginRequest(username,password)
        }
    }

    private fun loginRequest(username: String, password: String) {
        var url:String = "https://tim7.ictcortex.me/api/login"
        var requestQueue:RequestQueue= Volley.newRequestQueue(this)
        var stringRequest:StringRequest = object :
        StringRequest(Request.Method.POST,url,Response.Listener{ response ->

            Log.i("username", password)

            var res = Gson().fromJson(
                response,
                ResponseLogin::class.java
            ) as ResponseLogin


            if(res.msg == "success"){
                var tokenM = res.plainTextToken
                val sharedPreference =  getSharedPreferences("MySharedPref", MODE_PRIVATE)
                var editor = sharedPreference.edit()
                editor.putString("token",tokenM)
                editor.commit()
                var i = Intent(this,mojipravizahtjevi::class.java)
                startActivity(i)
            }
            else{
                Toast.makeText(this,"Provjerite Username iili sifru",Toast.LENGTH_LONG).show()
            }
        }, Response.ErrorListener { error->
            Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()
        }){
            override fun getParams(): MutableMap<String, String>? {
                val params=HashMap<String,String>()
                params.put("username",username)
                params.put("password",password)
                return params
            }
        }

        requestQueue.add(stringRequest)
    }

    fun logInMain(){

        setContentView(R.layout.login_main_layout)

        val btnLogin = findViewById<Button>(R.id.login_btn_log_in)
        val btnPristup = findViewById<TextView>(R.id.login_text_ne_mogu_da_pristupim)
        val btnNoNalog = findViewById<TextView>(R.id.login_text_nemas_nalog)

        btnLogin.setOnClickListener(){
            startActivity(Intent(this , NawDrawer::class.java))
        }
        btnPristup.setOnClickListener(){
            logInPristup()
        }

    }

    fun logInPristup(){

        setContentView(R.layout.login_forgot_layout)

        val btnNazad = findViewById<TextView>(R.id.forgot_login_text_nazad)
        val btnSubmit = findViewById<Button>(R.id.forgot_login_btn_submit)
        btnNazad.setOnClickListener(){
            logInMain()
        }

        btnSubmit.setOnClickListener(){
            logInZahtjev()
        }
    }

    fun logInZahtjev(){
        setContentView(R.layout.login_forgot_sent_password_layout)

        val btnNazad = findViewById<TextView>(R.id.reqeust_sent_password_text_povratak)

        btnNazad.setOnClickListener(){
            logInMain()
        }
    }

    fun strRequest(){
        val url = "https://tim7.ictcortex.me/api/"

    }
}