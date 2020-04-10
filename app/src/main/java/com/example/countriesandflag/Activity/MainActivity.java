package com.example.countriesandflag.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countriesandflag.CountriesAPI;
import com.example.countriesandflag.NetworkClient;
import com.example.countriesandflag.R;
import com.example.countriesandflag.adapter.CountriesAdapter;
import com.example.countriesandflag.pojo.Countries;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;


public class MainActivity extends AppCompatActivity {
    public final static String HOLIDAY = "holiday";
    NetworkClient networkClient = new NetworkClient();
    private RecyclerView listCountriesName;
    private CountriesAdapter adapterCountry;
    private ProgressBar progressBar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initRecyclerView();
    }
    
    private void clickAdapter() {
        CountriesAdapter.OnHolidayClickListener onHolidayClickListener = adapterPosition -> {
            String code = adapterCountry.returnDataCountry(adapterPosition).code;
            Intent intent = new Intent(MainActivity.this, HolidayActivity.class);
            intent.putExtra(MainActivity.HOLIDAY, code);
            startActivity(intent);
        };
        adapterCountry = new CountriesAdapter(onHolidayClickListener);
    }
    
    private void initRecyclerView() {
        listCountriesName = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);
        clickAdapter();
        
        listCountriesName.setAdapter(adapterCountry);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        listCountriesName.setLayoutManager(layoutManager);
        
        CountriesAPI countriesAPI = networkClient.getCountriesAPI();
        Call<Countries> countriesCall = countriesAPI.getCountries();
        countriesCall.enqueue(new Callback<Countries>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<Countries> call, Response<Countries> response) {
                Countries countriesResponse = response.body();
                
                assert countriesResponse != null;
                adapterCountry.setCountries(countriesResponse.countries);
                adapterCountry.notifyDataSetChanged();
                visibility();
            }
            
            @Override
            @EverythingIsNonNull
            public void onFailure(Call<Countries> call, Throwable t) {
                visibility();
            }
        });
    }
    
    private void visibility() {
        listCountriesName.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }
}
