package com.example.tom.projeto3_udacity.Activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import com.example.tom.projeto3_udacity.Adapters.StepsAdapter;
import com.example.tom.projeto3_udacity.Fragments.MasterListFragment;
import com.example.tom.projeto3_udacity.Fragments.StepDetailFragment;
import com.example.tom.projeto3_udacity.Model.Ingredient;
import com.example.tom.projeto3_udacity.Model.Recipe;
import com.example.tom.projeto3_udacity.Model.Step;
import com.example.tom.projeto3_udacity.R;

import java.util.List;

/**
 * Created by Tom on 21/04/2018.
 */

public class DetailsActivity extends AppCompatActivity implements MasterListFragment.OnStepSelectedListener{

    private int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        if(savedInstanceState != null){
            int postion = savedInstanceState.getInt("Position");
            if(postion != -1)  {
                onStepSelected(postion);
            }
        }
        else{
            StepDetailFragment stepDetailFragment = (StepDetailFragment) getSupportFragmentManager().findFragmentById(R.id.step_detail_fragment);
            if(stepDetailFragment == null) {
                MasterListFragment newFragment = new MasterListFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.commit();
            }
        }
    }


    @Override
    public void onStepSelected(int position) {
        this.position = position;
        StepDetailFragment stepDetailFragment = (StepDetailFragment) getSupportFragmentManager().findFragmentById(R.id.step_detail_fragment);
        if(stepDetailFragment != null){
            stepDetailFragment.setPosition(position);
        }else{
            StepDetailFragment newFragment = new StepDetailFragment();
            Bundle args = new Bundle();
            args.putInt("Position", position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Position", position);
    }

}
