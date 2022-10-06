package com.example.telekompraksa

import android.annotation.SuppressLint
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
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


class mojipravizahtjevi : AppCompatActivity() {

     lateinit var toggle:ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var url = "https://tim7.ictcortex.me/api/zahtjevi"
        var tokenString = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        var token: String = tokenString.getString("token", "").toString()
        setContentView(R.layout.activity_mojipravizahtjevi)
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
                R.id.thirdItem -> startActivity(Intent(this,AktivnostiPrikaz::class.java))
                R.id.forth -> startActivity(Intent(this,sve_knjige::class.java))
                R.id.fifthy -> startActivity(Intent(this,KnjigeNaRaspolaganaju::class.java))


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
                        object : TypeToken<ArrayList<ResponzeZahtjevi>>() {}.type
                    ) as ArrayList<ResponzeZahtjevi>

                    var reyclerView: RecyclerView = findViewById(R.id.moji_list)
                    var adapteric: zahtjevi_adapter = zahtjevi_adapter(res, this)
                    reyclerView.adapter = adapteric
                    reyclerView.layoutManager = LinearLayoutManager(this)
                    adapteric.naKlik={
                        val intent = Intent(this,ZahtjevDetalji::class.java)
                        intent.putExtra("zahtjev",it)
                        startActivity(intent)
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

            //Pass Your Parameters here
//            override fun getParams(): Map<String, String>? {
//                val params: MutableMap<String, String> = HashMap()
//                params["User"] = UserName
//                params["Pass"] = PassWord
//                return params
//            }
        }

//        val queue = Volley.newRequestQueue(applicationContext)
        val queue = Volley.newRequestQueue(applicationContext)
        queue.add(request)
        Log.i("Poslato", request.toString())

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}