package com.example.aicvirtualtour

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return true
    }
}