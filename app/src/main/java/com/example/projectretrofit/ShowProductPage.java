package com.example.projectretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.projectretrofit.Api_Interface.JsonApi;
import com.example.projectretrofit.Model.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowProductPage extends AppCompatActivity {

    private TextView ShowData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product_page);

        ShowData = findViewById(R.id.showtext);
        getPost();
    }

    public void getPost(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonApi jsonPlaceholderApi = retrofit.create(JsonApi.class);
        Call<List<Products>> call = jsonPlaceholderApi.getProduct();

        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                if(!response.isSuccessful()){
                    ShowData.setText("Code :"+response.code());
                    return;
                }

                List<Products> products = response.body();
                for(Products product:products){
                    String content ="";
                    content += "Id: " +product.getId()+ "\n";
                    content += "Product Name: " +product.getProductName()+ "\n";
                    content += "Category: " +product.getCategory()+ "\n";
                    content += "Sub-Category: " +product.getSubCategory()+ "\n";
                    content += "Product Price: " +product.getPrice()+ "\n\n";

                    ShowData.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                ShowData.setText(t.getMessage());
            }
        });
    }
}