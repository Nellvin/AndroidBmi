package com.example.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val actionBar = supportActionBar
        actionBar!!.title = "Info"
        Toast.makeText(this, "Niestety Jeszcze Tu nie doszedłem :c", LENGTH_SHORT).show()
    }
}
