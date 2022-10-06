package com.example.telekompraksa

import android.content.ClipData
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.telekompraksa.databinding.ActivityNawDrawerBinding

class NawDrawer : AppCompatActivity()  {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNawDrawerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        print("ajdeeeeeeeeeeeeeeeeee")
        print(R.id.profilePic)
        // moj pokusaj ovdje

        val profilePic = findViewById<ImageView>(R.id.profilePic)
        profilePic.setOnClickListener(){
            startActivity(Intent(this, NalogActivity::class.java))
        }

        binding = ActivityNawDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarNawDrawer.toolbar)

        binding.appBarNawDrawer.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_naw_drawer)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
       appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val mNavigationView = findViewById<View>(R.id.nav_view) as NavigationView
        mNavigationView.setNavigationItemSelectedListener  { it: MenuItem ->
            when (it.itemId) {
                R.id.naw_zahtjevi -> naw_zahtjevi()
                R.id.naw_lista_zelja-> naw_lista_zelja()
                else -> {
                    true
                }
            }
        }
    }

    fun naw_zahtjevi(): Boolean
    {
        Log.d("MENU", "naw_zahtjevi")
        return true

    }

    fun naw_lista_zelja(): Boolean
    {
        Log.d("MENU", "naw_lista_zelja")
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.naw_drawer, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_naw_drawer)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}