package com.example.telekompraksa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import layout.PaginiraniZanr
import layout.ZanrAdapter

class sve_knjige : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sve_knjige)
        var url = "http://tim7.ictcortex.me/api/search-books?enable_pagination=false"

        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.nav_viewv)
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
                R.id.forth -> startActivity(Intent(this,KnjigeNaRaspolaganaju::class.java))


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
                        object : TypeToken<ArrayList<KnjigaModel>>() {}.type
                    ) as ArrayList<KnjigaModel>
                    if(res.size>8){
                        while (res.size!=8){
                            res.removeAt(res.size-1)
                        }
                    }
                    Log.i("rezultat", res.toString())
                    var reyclerView: RecyclerView = findViewById(R.id.moji_list)
                    var adapteric: odAutoraAdapter = odAutoraAdapter(res, this)
                    reyclerView.adapter = adapteric
                    reyclerView.setHasFixedSize(true)
                    reyclerView.setLayoutManager(GridLayoutManager(this, 2))
                    adapteric.naKlik={
                        val intent = Intent(this,KnjigeNaRaspolaganaju::class.java)
//                        intent.putExtra("knjiga",it)
                        startActivity(intent)
                    }

                } else {
                    Log.e("Your Array Response", "Data Null")
                }
            },
            Response.ErrorListener { error -> Log.e("error is ", error.toString()) }) {
            //
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
                return params
            }



        }
        val queue1 = Volley.newRequestQueue(applicationContext)

        queue1.add(request)
//        Log.i("Poslato", request.toString())
//        var url1="http://tim7.ictcortex.me/api/zanrovi?enable_pagination=false"
//        val request1: StringRequest = object : StringRequest(
//            Method.GET, url1,
//            Response.Listener { response ->
//                if (response != null) {
//                    Log.i("Your Array Response", response)
//
//                    var res = Gson().fromJson(
//                        response,
//                        object : TypeToken<ArrayList<ZanrModel>>() {}.type
//                    ) as ArrayList<ZanrModel>
//                    if(res.size>8){
//                        while (res.size!=8){
//                            res.removeAt(res.size-1)
//                        }
//                    }
//                    Log.i("rezultat", res.toString())
//                    var reyclerView: RecyclerView = findViewById(R.id.moji_list1)
//                    var adapteric: ZanrAdapter = ZanrAdapter(res, this)
//                    reyclerView.adapter = adapteric
//                    reyclerView.setHasFixedSize(true)
//                    reyclerView.setLayoutManager(GridLayoutManager(this, 2))
//
//
//                } else {
//                    Log.e("Your Array Response", "Data Null")
//                }
//            },
//            Response.ErrorListener { error -> Log.e("error is ", error.toString()) }) {
//            //
//            @Throws(AuthFailureError::class)
//            override fun getHeaders(): Map<String, String> {
//                val params: MutableMap<String, String> = HashMap()
//                params["Content-Type"] = "application/json; charset=UTF-8"
//                var tokenString = getSharedPreferences("MySharedPref", MODE_PRIVATE)
//                var token: String = tokenString.getString("token", "").toString()
//                params["Authorization"] = "Bearer $token"
//                return params
//            }
//
//
//            override fun getParams(): Map<String, String>? {
//                val params: MutableMap<String, String> = HashMap()
//                params["enable_pagination"] = false.toString()
//                return params
//            }
//
//
//
//        }
//        val queue = Volley.newRequestQueue(applicationContext)
//
//        queue.add(request1)
//        Log.i("Poslato", request1.toString())
//
//        var url2="http://tim7.ictcortex.me/api/zanrovi?enable_pagination=false"
//        val request2: StringRequest = object : StringRequest(
//            Method.GET, url2,
//            Response.Listener { response ->
//                if (response != null) {
//                    Log.i("Your Array Response", response)
//
//                    var res = Gson().fromJson(
//                        response,
//                        object : TypeToken<ArrayList<KategorijaModel>>() {}.type
//                    ) as ArrayList<KategorijaModel>
//                    if(res.size>8){
//                        while (res.size!=8){
//                            res.removeAt(res.size-1)
//                        }
//                    }
//                    Log.i("rezultat", res.toString())
//                    var reyclerView: RecyclerView = findViewById(R.id.moji_list1)
//                    var adapteric: KategorijaAdapter = KategorijaAdapter(res, this)
//                    reyclerView.adapter = adapteric
//                    reyclerView.setHasFixedSize(true)
//                    reyclerView.setLayoutManager(GridLayoutManager(this, 2))
//
//
//                } else {
//                    Log.e("Your Array Response", "Data Null")
//                }
//            },
//            Response.ErrorListener { error -> Log.e("error is ", error.toString()) }) {
//            //
//            @Throws(AuthFailureError::class)
//            override fun getHeaders(): Map<String, String> {
//                val params: MutableMap<String, String> = HashMap()
//                params["Content-Type"] = "application/json; charset=UTF-8"
//                var tokenString = getSharedPreferences("MySharedPref", MODE_PRIVATE)
//                var token: String = tokenString.getString("token", "").toString()
//                params["Authorization"] = "Bearer $token"
//                return params
//            }
//
//
//            override fun getParams(): Map<String, String>? {
//                val params: MutableMap<String, String> = HashMap()
//                params["enable_pagination"] = false.toString()
//                return params
//            }
//
//
//
//        }
//        val queue2 = Volley.newRequestQueue(applicationContext)
//
//        queue2.add(request2)
//        Log.i("Poslato", request2.toString())
    }
}