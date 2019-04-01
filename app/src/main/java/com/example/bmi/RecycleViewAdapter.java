package com.example.bmi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter
        .ViewHolder>{

    private ArrayList<String> mWeight = new ArrayList<>();
    private ArrayList<String> mHeight = new ArrayList<>();
    private ArrayList<String> mMeasures = new ArrayList<>();
    private ArrayList<String> mBMI = new ArrayList<>();
    private ArrayList<String> mDate = new ArrayList<>();
    private Context mContex;

    public RecycleViewAdapter(ArrayList<String> mWeight, ArrayList<String> mHeight, ArrayList<String> mMeasures, ArrayList<String> mBMI, ArrayList<String> mDate, Context mContex) {
        this.mWeight = mWeight;
        this.mHeight = mHeight;
        this.mMeasures = mMeasures;
        this.mBMI = mBMI;
        this.mDate = mDate;
        this.mContex = mContex;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("ResourceType") View view = LayoutInflater.from(parent.getContext()).inflate(R.id.parent_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
               holder.number.setText(position);
               holder.weight.setText(mWeight.get(position));
               holder.height.setText(mHeight.get(position));
               holder.measures.setText(mMeasures.get(position));
               holder.BMI.setText(mBMI.get(position));
               holder.Date.setText(mDate.get(position));
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView number;
        TextView weight;
        TextView height;
        TextView measures;
        TextView Date;
        TextView BMI;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            number=itemView.findViewById(R.id.textView7);
            weight=itemView.findViewById(R.id.textView8);
            height=itemView.findViewById(R.id.textView9);
            measures=itemView.findViewById(R.id.textView12);
            BMI=itemView.findViewById(R.id.textView13);
            Date=itemView.findViewById(R.id.textView14);
            parentLayout=itemView.findViewById(R.id.parent_layout);
        }
    }
}
