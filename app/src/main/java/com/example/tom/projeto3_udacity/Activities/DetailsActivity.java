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

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

    }


}
