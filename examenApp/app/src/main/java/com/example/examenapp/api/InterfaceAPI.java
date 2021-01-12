package com.example.examenapp.api;


import com.example.examenapp.models.LoginRequest;
import com.example.examenapp.models.LoginResponse;
import com.example.examenapp.models.RegisterUserRequest;
import com.example.examenapp.models.RegisterUserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface InterfaceAPI {

    @POST("login")
    Call<LoginResponse> userlogin(@Body LoginRequest loginRequest);

    @POST("register")
    Call<RegisterUserResponse> createUser(@Body RegisterUserRequest registerUserRequest);

}