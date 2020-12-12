package com.example.projectretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectretrofit.Api_Interface.JsonApi;
import com.example.projectretrofit.Model.Products;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

   private TextView AddData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddData = findViewById(R.id.text);
        createPost();
    }

    public void createPost(){
        Products products = new Products("","Watch","Boy","Accessory",39);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonApi jsonPlaceholderApi = retrofit.create(JsonApi.class);
        Call<Products> call = jsonPlaceholderApi.createProduct(products);
        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                if(!response.isSuccessful()){
                    AddData.setText("Code :"+response.code());
                    return;
                }
                Products postResponse =  response.body();

                String content ="";
                content += "Code :" +response.code() + "\n";
                content += "Product Name: " +products.getProductName()+ "\n";
                content += "Category: " +products.getCategory()+ "\n";
                content += "Sub-Category: " +products.getSubCategory()+ "\n";
                content += "Product Price: " +products.getPrice()+ "\n";
                AddData.append(content);
                Toast.makeText(MainActivity.this,"New Product Added",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                AddData.setText(t.getMessage());
            }
        });
    }

}
