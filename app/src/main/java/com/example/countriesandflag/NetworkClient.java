package com.example.countriesandflag;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {
    private static final String BASE_URL = "https://holidayapi.com/v1/";
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    
    
    public CountriesAPI getCountriesAPI() {
        return countriesAPI;
    }
    
    private CountriesAPI countriesAPI = retrofit.create(CountriesAPI.class);
}
