package com.example.projectretrofit.Api_Interface;

import com.example.projectretrofit.Model.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonApi {

    @GET("/select/")
    Call<List<Products>> getProduct();

    @POST("/dataadded/")
    Call<Products> createProduct(@Body Products products);
}
