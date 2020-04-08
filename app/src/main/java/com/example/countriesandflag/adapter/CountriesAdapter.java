package com.example.countriesandflag.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countriesandflag.R;
import com.example.countriesandflag.pojo.Country;

public class CountriesAdapter extends RecyclerView.Adapter<ViewHolderCountries> {
    private OnHolidayClickListener listener;
    private Country[] countries = new Country[0];
    
    public CountriesAdapter(OnHolidayClickListener listener) {
        this.listener = listener;
    }
    
    @NonNull
    @Override
    public ViewHolderCountries onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_coutries, parent, false);
        return new ViewHolderCountries(view, listener);
        
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHolderCountries holder, int position) {
        holder.bind(countries[position]);
        
    }
    
     @Override
    public int getItemCount() {
        return countries.length;
    }
    
    public void setCountries(Country[] countries) {
        this.countries = countries;
    }
    
    public OnHolidayClickListener getListener() {
        return listener;
    }
    
    public interface OnHolidayClickListener {
        void OnHolidayClick(int adapterPosition);
    }
}
