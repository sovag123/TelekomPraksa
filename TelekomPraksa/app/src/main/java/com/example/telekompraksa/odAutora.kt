package com.example.telekompraksa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.telekompraksa.ui.Knjiga
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class odAutora : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val autor = intent.getParcelableExtra<Autor>("pisac")
        var url = "https://tim7.ictcortex.me/api/search-books"
        setContentView(R.layout.activity_od_autora)

    }
}