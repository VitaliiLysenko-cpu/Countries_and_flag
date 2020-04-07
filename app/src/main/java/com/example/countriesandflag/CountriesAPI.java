package com.example.countriesandflag;

import com.example.countriesandflag.pojo.Countries;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountriesAPI {
    @GET ("countries?key=1b1cfc59-0d09-4182-b045-0c0f44c9ba4f")
    Call<Countries> getCountries();
}
