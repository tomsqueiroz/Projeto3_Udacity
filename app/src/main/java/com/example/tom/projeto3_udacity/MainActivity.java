package com.example.tom.projeto3_udacity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tom.projeto3_udacity.Model.Recipe;
import com.example.tom.projeto3_udacity.NetworkUtils.RetrofitInterface;
import com.example.tom.projeto3_udacity.NetworkUtils.RetrofitUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofitInterface = RetrofitUtils.getRecipesService();
        loadRecipes();


    }

    private void loadRecipes(){
        retrofitInterface.getRecipes().enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                int i = 0;
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                int i = 0;
            }
        });
    }
}
