package com.example.telekompraksa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AktivnostiPrikaz : AppCompatActivity() {
    lateinit var toggle:ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aktivnosti_prikaz)
        var url = "https://tim7.ictcortex.me/api/aktivnosti"
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView =findViewById(R.id.nav_viewv)
        toggle= ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val header = navView.getHeaderView(0) as LinearLayout
        var brat=header.findViewById<ImageView>(R.id.klikNavigacije)
        var brojac=1

        navView.menu.setGroupEnabled(R.id.novoo,false)
        navView.menu.setGroupVisible(R.id.novoo,false)

        brat.setOnClickListener{



            brojac++
            if(brojac%2==0) {
                brat.setImageResource(R.drawable.ic_tl_x)
                navView.menu.setGroupEnabled(R.id.novoo,true)
                navView.menu.setGroupVisible(R.id.novoo,true)
                navView.menu.setGroupVisible(R.id.settings,false)
                navView.menu.setGroupVisible(R.id.settings,false)
                navView.menu.setGroupVisible(R.id.grp1,false)
                navView.menu.setGroupVisible(R.id.grp1,false)

            }
//            navView.menu.gr
            else{
                brat.setImageResource(R.drawable.ic_tl_srick)
                navView.menu.setGroupEnabled(R.id.novoo,false)
                navView.menu.setGroupVisible(R.id.novoo,false)
                navView.menu.setGroupVisible(R.id.settings,true)
                navView.menu.setGroupVisible(R.id.settings,true)
                navView.menu.setGroupVisible(R.id.grp1,true)
                navView.menu.setGroupVisible(R.id.grp1,true)
            }

        }
        navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.firstItem ->{
                    startActivity(Intent(this,NalogActivity::class.java))
                }

                R.id.secondItem ->  startActivity(Intent(this,ListaZelja::class.java))
                R.id.thirdItem -> Toast.makeText(
                    applicationContext,
                    "Clicked first",
                    Toast.LENGTH_SHORT
                ).show()

            }
            true
        }
        val request: StringRequest = object : StringRequest(
            Method.GET, url,
            Response.Listener { response ->
                if (response != null) {
                    Log.i("Your Array Response", response)
                    var res = Gson().fromJson(
                        response,
                        object : TypeToken<ArrayList<Aktivnost>>() {}.type
                    ) as ArrayList<Aktivnost>

                    var reyclerView: RecyclerView = findViewById(R.id.jes)
                    var adapteric: AktivnostiAdapter = AktivnostiAdapter(res, this)
                    reyclerView.adapter = adapteric
                    reyclerView.layoutManager = LinearLayoutManager(this)
                    adapteric.naKlik={
//                        val intent = Intent(this,ZahtjevDetalji::class.java)
//                        intent.putExtra("zahtjev",it)
//                        startActivity(intent)
                    }

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


        }


        val queue = Volley.newRequestQueue(applicationContext)
        queue.add(request)
        Log.i("Poslato", request.toString())
    }
}