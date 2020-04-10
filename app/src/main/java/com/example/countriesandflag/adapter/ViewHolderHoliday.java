package com.example.countriesandflag.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countriesandflag.R;
import com.example.countriesandflag.pojo.Holiday;


public class ViewHolderHoliday extends RecyclerView.ViewHolder {
    
    private TextView name;
    private TextView date;
    
    public ViewHolderHoliday(@NonNull View itemView) {
        super(itemView);
        
        name = itemView.findViewById(R.id.text_name_holiday);
        date = itemView.findViewById(R.id.date_holiday);
    }
    
    void bind(Holiday holiday) {
        
        name.setText(holiday.name);
        date.setText(holiday.date);
    }
}
