package com.example.telcoapp.api;

import com.example.telcoapp.model.EventRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface EventApiService {
    @POST("app/events")
    Call<Void> sendEvent(@Body EventRequest eventRequest);
}
