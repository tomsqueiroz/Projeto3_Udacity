package com.example.tom.projeto3_udacity.NetworkUtils;

import com.example.tom.projeto3_udacity.Model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Tom on 21/04/2018.
 */

public interface RetrofitInterface {

    @GET("/topher/2017/May/59121517_baking/baking.json")
    Call<List<Recipe>> getRecipes();

}
