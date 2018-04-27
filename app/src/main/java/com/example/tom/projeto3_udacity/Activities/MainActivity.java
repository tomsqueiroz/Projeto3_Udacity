package com.example.tom.projeto3_udacity.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tom.projeto3_udacity.Activities.DetailsActivity;
import com.example.tom.projeto3_udacity.Adapters.RecipesAdapter;
import com.example.tom.projeto3_udacity.Model.Recipe;
import com.example.tom.projeto3_udacity.NetworkUtils.NetworkUtils;
import com.example.tom.projeto3_udacity.NetworkUtils.RetrofitInterface;
import com.example.tom.projeto3_udacity.NetworkUtils.RetrofitUtils;
import com.example.tom.projeto3_udacity.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecipesAdapter.RecipesAdapterOnClickHandler {

    private RetrofitInterface retrofitInterface;

    private ProgressBar pb;
    private RecyclerView mRecyclerView;
    private List<Recipe> recipeList;
    private LinearLayoutManager layoutManager;
    private RecipesAdapter mRecipesAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb = (ProgressBar) findViewById(R.id.pb_main);
        pb.setVisibility(View.INVISIBLE);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_main);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecipesAdapter = new RecipesAdapter(this, this);
        mRecyclerView.setAdapter(mRecipesAdapter);


        retrofitInterface = RetrofitUtils.getRecipesService();
        if(NetworkUtils.connection_ok(this)) loadRecipes();
        else{
            Toast toast = Toast.makeText(getApplicationContext(), "Sem Conexão à Internet", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void loadRecipes(){
        retrofitInterface.getRecipes().enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                recipeList = response.body();
                mRecipesAdapter.setRecipeList(recipeList);
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                int i = 0;
            }
        });
    }



    @Override
    public void onClick(Recipe recipe) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("Receita", recipe);
        Intent i = new Intent(this, DetailsActivity.class);
        i.putExtra("Receita", bundle);
        startActivity(i);
    }
}
