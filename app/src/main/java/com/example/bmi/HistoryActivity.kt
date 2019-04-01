package com.example.bmi

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
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
        initList()
        val adapter = CustomAdapter(mWeight, mHeight, mMeasures, mBMI, mDate)

        recyclerView.adapter = adapter
    }

    private fun initList() {
        mWeight.add("60")
        mWeight.add("70")
        mWeight.add("80")
        mWeight.add("90")
        mWeight.add("100")
        mWeight.add("110")
        mWeight.add("120")
        mWeight.add("130")
        mWeight.add("140")
        mWeight.add("150")
        mHeight.add("110")
        mHeight.add("120")
        mHeight.add("130")
        mHeight.add("140")
        mHeight.add("150")
        mHeight.add("160")
        mHeight.add("170")
        mHeight.add("180")
        mHeight.add("190")
        mHeight.add("200")
        mMeasures.add("kg/cm")
        mMeasures.add("kg/cm")
        mMeasures.add("kg/cm")
        mMeasures.add("kg/cm")
        mMeasures.add("kg/cm")
        mMeasures.add("kg/cm")
        mMeasures.add("kg/cm")
        mMeasures.add("kg/cm")
        mMeasures.add("kg/cm")
        mMeasures.add("kg/cm")
        mBMI.add("22.22")
        mBMI.add("22.22")
        mBMI.add("22.22")
        mBMI.add("22.22")
        mBMI.add("22.22")
        mBMI.add("22.22")
        mBMI.add("22.22")
        mBMI.add("22.22")
        mBMI.add("22.22")
        mBMI.add("22.22")
        // val today = Calendar.getInstance()
        mDate.add("now")
        mDate.add("now")
        mDate.add("now")
        mDate.add("now")
        mDate.add("now")
        mDate.add("now")
        mDate.add("now")
        mDate.add("now")
        mDate.add("now")
        mDate.add("now")

    }
}
