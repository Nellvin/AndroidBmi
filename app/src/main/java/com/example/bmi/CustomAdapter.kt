package com.example.bmi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(
    val mWeight: ArrayList<String>,
    val mHeight: ArrayList<String>,
    val mMeasures: ArrayList<String>,
    val mBMI: ArrayList<String>,
    val mDate: ArrayList<String>,
    val mAr :ArrayList<String>
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_listitem, parent, false)
        return ViewHolder(v)
    }


    override fun getItemCount(): Int {
        return mAr.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val combi= mAr[position].split("|")

        holder.number.text = (position+1).toString()
        holder.height.text = combi[0]
        holder.weight.text = combi[1]
        holder.measures.text = combi[2]
        holder.BMI.text = combi[3]
        holder.Date.text = combi[4]
        holder.BMI.setTextColor(combi[5].toInt())
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val number = itemView.findViewById(R.id.textView7) as TextView
        val weight = itemView.findViewById(R.id.textView8) as TextView
        val height = itemView.findViewById(R.id.textView9) as TextView
        val measures = itemView.findViewById(R.id.textView12) as TextView
        val BMI = itemView.findViewById(R.id.textView13) as TextView
        val Date = itemView.findViewById(R.id.textView14) as TextView
    }


}