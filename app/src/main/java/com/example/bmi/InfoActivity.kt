package com.example.bmi


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            textView10.textSize= 30.0F
        }
        else {
            textView10.text = "Bez podania danych dużo Ci nie pomogę :/"
            textView10.textSize= 20.0F
            textView11.text= ""
        }



    }
}
