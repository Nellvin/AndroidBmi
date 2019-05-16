package com.example.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val actionBar = supportActionBar
        actionBar!!.title = "History"
        val recyclerView = findViewById(R.id.recycleView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val historyArr = intent.getStringArrayListExtra("arr")
        val adapter = CustomAdapter(historyArr)
        recyclerView.adapter = adapter
    }
}
