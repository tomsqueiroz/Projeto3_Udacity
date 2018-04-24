package com.example.tom.projeto3_udacity.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import com.example.tom.projeto3_udacity.Adapters.StepsAdapter;
import com.example.tom.projeto3_udacity.Model.Ingredient;
import com.example.tom.projeto3_udacity.Model.Recipe;
import com.example.tom.projeto3_udacity.Model.Step;
import com.example.tom.projeto3_udacity.R;

import java.util.List;

/**
 * Created by Tom on 21/04/2018.
 */

public class DetailsActivity extends AppCompatActivity implements StepsAdapter.StepsAdapterOnClickHandler{

    private Recipe recipe;
    private TextView tv_description;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private StepsAdapter mStepsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent i = getIntent();
        Bundle b = i.getBundleExtra("Receita");
        recipe = b.getParcelable("Receita");
        tv_description = (TextView) findViewById(R.id.tv_description);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_steps);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mStepsAdapter = new StepsAdapter(this, this);
        mRecyclerView.setAdapter(mStepsAdapter);
        mStepsAdapter.setstepList(recipe.getSteps());
        ingredientsToTextView();

    }

    public void ingredientsToTextView(){
        tv_description.setText(getString(R.string.ingredientsDetailsLabel) + "\n");
        for(Ingredient ingredient : recipe.getIngredients()){
            tv_description.append("- " + ingredient.getIngredient());
            tv_description.append(" (" + ingredient.getQuantity() + " " +  ingredient.getMeasure() + ")\n");
        }
    }


    @Override
    public void onClick(int position) {
        Bundle b = new Bundle();
        b.putParcelable("Receita", recipe);
        b.putInt("Position", position);
        Intent intent = new Intent(this, StepDetailActivity.class);
        intent.putExtra("Receita", b);
        startActivity(intent);
    }
}
