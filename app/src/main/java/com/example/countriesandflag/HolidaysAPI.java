package com.example.countriesandflag;

import com.example.countriesandflag.pojo.Holidays;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface HolidaysAPI {
    @GET("holidays?key=1b1cfc59-0d09-4182-b045-0c0f44c9ba4f&year=2019")
    Call<Holidays> getHolidays (@Query("country") String code);
}
