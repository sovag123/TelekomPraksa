package com.example.telekompraksa

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class LoginActivity: AppCompatActivity() {
    val URL_REGISTER = "https://tim7.ictcortex.me/api/"
    val sharedprofFile = "com.example.telekompraksa"
    var is_signed_in = ""

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {

        super.onCreate(savedInstanceState, persistentState)
        val mPreferences = getSharedPreferences(sharedprofFile, MODE_PRIVATE)
        val preferencesEditor = mPreferences.edit()
        is_signed_in = mPreferences.getString("issignedin","false").toString()
        if(is_signed_in.equals("true"))
        {
//            var i = Intent(LoginActivity.this,MainActivity.class)

        }
    }

}