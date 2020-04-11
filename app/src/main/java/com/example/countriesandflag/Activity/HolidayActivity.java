package com.example.countriesandflag.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countriesandflag.HolidaysAPI;
import com.example.countriesandflag.NetworkClient;
import com.example.countriesandflag.R;
import com.example.countriesandflag.adapter.HolidayAdapter;
import com.example.countriesandflag.pojo.Holidays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.countriesandflag.Activity.MainActivity.HOLIDAY;

public class HolidayActivity extends AppCompatActivity {
   
    private RecyclerView recyclerHolidayList;
    NetworkClient networkClient = new NetworkClient();
    private HolidayAdapter holidayAdapter;
    private ProgressBar progressBar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holiday);
        initRecyclerHolidayList();
    }
    
    private void initRecyclerHolidayList(){
        holidayAdapter = new HolidayAdapter();
        recyclerHolidayList = findViewById(R.id.recycler_view_holiday);
        progressBar = findViewById(R.id.progress_bar_holiday);
        recyclerHolidayList.setAdapter(holidayAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerHolidayList.setLayoutManager(layoutManager);
        
        Intent codeCoutry = getIntent();
        String code = codeCoutry.getStringExtra(HOLIDAY);
        HolidaysAPI holidaysAPI = networkClient.getHolidayAPI();
        Call<Holidays> holidaysCall = holidaysAPI.getHolidays(code);
        holidaysCall.enqueue(new Callback<Holidays>() {
            @Override
            public void onResponse(@NonNull Call<Holidays>  call, @NonNull Response<Holidays> response) {
                    Holidays holidaysResponse = response.body();
                    assert holidaysResponse != null;
                    holidayAdapter.setHolidays(holidaysResponse.holidays);
                    holidayAdapter.notifyDataSetChanged();
                    visibility();
            }
    
            @Override
            public void onFailure(@NonNull Call<Holidays> call, @NonNull Throwable t) {
              visibility();
            }
        });
    }
    private void visibility(){
      recyclerHolidayList.setVisibility(View.VISIBLE);
      progressBar.setVisibility(View.GONE);
    }
}
