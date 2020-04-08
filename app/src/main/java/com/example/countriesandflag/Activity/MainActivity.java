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


public class MainActivity extends AppCompatActivity {
    private final static String HOLIDAY = "holiday";
    NetworkClient networkClient = new NetworkClient();
    private RecyclerView listCountriesName;
    private CountriesAdapter adapter;
    private ProgressBar progressBar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initRecyclerView();
    }
    
    private void clickAdapter() {
        CountriesAdapter.OnHolidayClickListener onHolidayClickListener = adapterPosition -> {
            adapter.getItemId(adapterPosition);
            
            Intent intent = new Intent(MainActivity.this, Holiday.class);
            intent.putExtra(MainActivity.HOLIDAY, adapterPosition);
            startActivity(intent);
        };
        adapter = new CountriesAdapter(onHolidayClickListener);
    }
    
    private void initRecyclerView() {
        listCountriesName = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);
        clickAdapter();
        
        listCountriesName.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        listCountriesName.setLayoutManager(layoutManager);
        
        CountriesAPI countriesAPI = networkClient.getCountriesAPI();
        Call<Countries> countriesCall = countriesAPI.getCountries();
        countriesCall.enqueue(new Callback<Countries>() {
            @Override
            public void onResponse(Call<Countries> call, Response<Countries> response) {
                Countries countriesResponse = response.body();
                
                adapter.setCountries(countriesResponse.countries);
                adapter.notifyDataSetChanged();
                visibility();
            }
            
            @Override
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
