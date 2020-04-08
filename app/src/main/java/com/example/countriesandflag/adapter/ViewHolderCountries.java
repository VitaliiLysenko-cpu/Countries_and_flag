package com.example.countriesandflag.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countriesandflag.R;
import com.example.countriesandflag.pojo.Country;
import com.squareup.picasso.Picasso;


public class ViewHolderCountries extends RecyclerView.ViewHolder {
    
    private TextView name;
    private ImageView flag;
    private CountriesAdapter.OnHolidayClickListener onHolidayClickListener;
    
    public ViewHolderCountries(@NonNull final View itemView
            , final CountriesAdapter.OnHolidayClickListener onHolidayClickListener) {
        super(itemView);
        name = itemView.findViewById(R.id.text_name_country);
        flag = itemView.findViewById(R.id.flag_country);
        
        this.onHolidayClickListener = onHolidayClickListener;
    }
    
    public void bind(Country country) {
        
        name.setText(country.name);
        Picasso.get()
                .load(country.flag)
                .into(flag);
        itemView.setOnClickListener(v -> onHolidayClickListener.OnHolidayClick(getAdapterPosition()));
    }
}
