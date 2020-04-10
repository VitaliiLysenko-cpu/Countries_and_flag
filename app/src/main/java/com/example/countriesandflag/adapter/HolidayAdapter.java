package com.example.countriesandflag.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countriesandflag.R;
import com.example.countriesandflag.pojo.Holiday;

public class HolidayAdapter extends RecyclerView.Adapter<ViewHolderHoliday> {
    
    private Holiday[] holidays = new Holiday[0] ;
    
    @NonNull
    @Override
    public ViewHolderHoliday onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_holiday, parent ,false);
        return new ViewHolderHoliday(view);
    }
   
    @Override
    public void onBindViewHolder(@NonNull ViewHolderHoliday holder, int position) {
            holder.bind(holidays[position]);
    }
    
    @Override
    public int getItemCount() {
        return holidays.length;
    }
    public Holiday[] getHolidays() {
        return holidays;
    }
    
    public void setHolidays(Holiday[] holidays) {
        this.holidays = holidays;
    }
}
