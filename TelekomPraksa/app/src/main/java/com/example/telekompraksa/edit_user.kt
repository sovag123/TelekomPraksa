package com.example.telekompraksa

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.google.gson.Gson

class edit_user : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)
        val prof = intent.getParcelableExtra<User>("profil")

        val slika: ImageView = findViewById(R.id.slikaProf2)
        val imeP: EditText = findViewById(R.id.editTextTextPersonName)
        val email: EditText = findViewById(R.id.editTextTextEmailAddress)
        val user: EditText = findViewById(R.id.editTextTextPersonName2)
        val stara: EditText = findViewById(R.id.editTextTextPassword)
        val nova: EditText = findViewById(R.id.editTextTextPassword2)
        val novaP: EditText = findViewById(R.id.editTextTextPassword3)
        val sac: Button = findViewById(R.id.sads)
        val ponisti: Button = findViewById(R.id.sads)
        val slikica = "https://tim7.ictcortex.me/storage/image/" + prof?.photo
        var url = "https://tim7.ictcortex.me/api/edit-user"
        Glide.with(this).load(slikica).centerCrop().placeholder(R.drawable.ic_cortex)
            .into(slika)
        imeP.setText(prof?.name)
        email.setText(prof?.email)
        user.setText(prof?.username)
        ponisti.setOnClickListener {
            val intent = Intent(this,NalogActivity::class.java)

            startActivity(intent)
        }
        sac.setOnClickListener {
            if (stara.text.toString() != "" && nova.text.toString() != "" && novaP.text.toString() != "") {
                if (novaP.text == nova.text) {
                    val request: StringRequest = object : StringRequest(
                        Method.POST, url,
                        Response.Listener { response ->
                            if (response != null) {
                                Log.i("Your Array Response", response)
                                val intent = Intent(this,NalogActivity::class.java)

                                startActivity(intent)

                            } else {
                                Log.e("Your Array Response", "Data Null")
                            }
                        },
                        Response.ErrorListener { error -> Log.e("error is ", error.toString()) }) {
                        override fun getParams(): Map<String, String>? {
                            val params: MutableMap<String, String> = HashMap()
                            params["name"] = imeP.text.toString()
                            params["email"] = email.text.toString()
                            params["username"] = user.text.toString()
                            params["oldPass"] = stara.text.toString()
                            params["newPass"] = novaP.text.toString()
                            return params
                        }

                    }
                    val queue = Volley.newRequestQueue(applicationContext)
                    queue.add(request)
                    Log.i("Poslato", request.toString())
                }
            }
        }


    }
}