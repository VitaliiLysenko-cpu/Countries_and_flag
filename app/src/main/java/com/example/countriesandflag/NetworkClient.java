package com.example.countriesandflag;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {
    private static final String BASE_URL = "https://holidayapi.com/v1/";
   
  
    private Retrofit retrofit;
    private CountriesAPI countriesAPI;
    private HolidaysAPI holidaysAPI;
    
    public NetworkClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY) ;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        countriesAPI = retrofit.create(CountriesAPI.class);
        holidaysAPI = retrofit.create(HolidaysAPI.class);
    }
    
    public CountriesAPI getCountriesAPI() {
        return countriesAPI;
    }
    
    public HolidaysAPI getHolidayAPI() {
        return holidaysAPI;
    }
}
