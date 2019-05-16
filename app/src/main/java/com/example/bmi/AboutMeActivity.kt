package com.example.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import kotlinx.android.synthetic.main.test.*

class AboutMeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_me)
        val actionBar = supportActionBar
        actionBar!!.title = "About Me"
        textView5.movementMethod = LinkMovementMethod.getInstance()
    }
}
