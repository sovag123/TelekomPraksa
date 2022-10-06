package com.example.telekompraksa

import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.telekompraksa.databinding.MojiZahtjeviBinding

class NavDraw:AppCompatActivity() {
    lateinit var  binding: MojiZahtjeviBinding
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)
        binding=MojiZahtjeviBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            toggle=ActionBarDrawerToggle(this@NavDraw,draweLayout,R.string.open,R.string.close)
            draweLayout.addDrawerListener(toggle)
            toggle.syncState()

            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navView.setNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.firstItem->{
                        Toast.makeText(this@NavDraw,"First Item Click", Toast.LENGTH_SHORT).show()}
                    R.id.secondItem->{Toast.makeText(this@NavDraw,"Second Item Click", Toast.LENGTH_SHORT).show()}
                    R.id.thirdItem->{Toast.makeText(this@NavDraw,"Third Item Click", Toast.LENGTH_SHORT).show()}

                }
                true
            }

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            true
        }
        return super.onOptionsItemSelected(item)
    }
//    private fun zahtjeviRequest{
//
//    }
}