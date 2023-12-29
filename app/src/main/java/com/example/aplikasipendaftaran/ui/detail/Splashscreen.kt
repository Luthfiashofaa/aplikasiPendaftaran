package com.example.aplikasipendaftaran.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.aplikasipendaftaran.R
import com.example.aplikasipendaftaran.ui.add.datapribadi.AddDataPribadi


class Splashscreen : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        Handler().postDelayed({
            startActivity(Intent(this, AddDataPribadi::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}