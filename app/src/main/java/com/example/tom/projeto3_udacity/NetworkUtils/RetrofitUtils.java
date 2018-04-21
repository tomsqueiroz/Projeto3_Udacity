package com.example.tom.projeto3_udacity.NetworkUtils;

/**
 * Created by Tom on 21/04/2018.
 */

public class RetrofitUtils {

    public static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/";

    public static RetrofitInterface getRecipesService() {
        return RetrofitClient.getClient(BASE_URL).create(RetrofitInterface.class);
    }

}
