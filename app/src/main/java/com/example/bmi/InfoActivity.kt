package com.example.bmi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val actionBar = supportActionBar
        actionBar!!.title = "Info"
        if(!intent.getStringExtra("bmiValue").equals("?")) {
            textView10.text = intent.getStringExtra("bmiValue")
            textView11.text= intent.getStringExtra("bmiValueInfo")
        }
        else {
            textView10.text = "Bez podania danych dużo Ci nie pomogę :/"
            textView11.text= ""
        }



    }
}
