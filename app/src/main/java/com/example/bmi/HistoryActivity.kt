package com.example.bmi

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_listitem.*
import java.util.*

class HistoryActivity : AppCompatActivity() {

    var mWeight = ArrayList<String>(10)
    var mHeight = ArrayList<String>(10)
    var mMeasures = ArrayList<String>(10)
    var mBMI = ArrayList<String>(10)
    var mDate = ArrayList<String>(10)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val actionBar = supportActionBar
        actionBar!!.title = "History"
        val recyclerView = findViewById(R.id.recycleView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        //init
        val ar = intent.getStringArrayListExtra("arr")
        val adapter = CustomAdapter(mWeight, mHeight, mMeasures, mBMI, mDate, ar)

        recyclerView.adapter = adapter
    }
}
