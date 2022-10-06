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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListaZelja : AppCompatActivity() {
    lateinit var toggle:ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_zelja)

        val settings = applicationContext.getSharedPreferences("moja brothers", MODE_PRIVATE)
        val lista = settings.getString("cuvanje", "")
        val objekatListe:ArrayList<KnjigaModel>
        val gsonic = Gson()
        if(gsonic.fromJson<ArrayList<KnjigaModel>>(lista,
                object : TypeToken<ArrayList<KnjigaModel>>() {}.type)!=null || lista.toString()!="") {
                Log.i("gsom", lista.toString())


             objekatListe = gsonic.fromJson<ArrayList<KnjigaModel>>(lista,
                object : TypeToken<ArrayList<KnjigaModel>>() {}.type)
        }
        else{
            objekatListe= ArrayList()
        }
        val navView: NavigationView =findViewById(R.id.nav_viewv)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        toggle= ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.menu.findItem(R.id.firstItem).setChecked(false)
        navView.menu.findItem(R.id.secondItem).setChecked(true)
        var reyclerView: RecyclerView = findViewById(R.id.moji_list)
        var adapteric: odAutoraAdapter = odAutoraAdapter(objekatListe, this)
        reyclerView.adapter = adapteric
        reyclerView.setHasFixedSize(true)
        reyclerView.setLayoutManager(GridLayoutManager(this, 2))
        adapteric.naKlik = {
            val intent = Intent(this, KnjigeNaRaspolaganaju::class.java)
            intent.putExtra("knjiga", it)
            startActivity(intent)
        }
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

                R.id.secondItem -> Toast.makeText(
                    applicationContext,
                    "Clicked first",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.thirdItem -> Toast.makeText(
                    applicationContext,
                    "Clicked first",
                    Toast.LENGTH_SHORT
                ).show()

            }
            true
        }
    }
}