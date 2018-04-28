package com.example.tom.projeto3_udacity.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tom.projeto3_udacity.Adapters.StepsAdapter;
import com.example.tom.projeto3_udacity.Model.Ingredient;
import com.example.tom.projeto3_udacity.Model.Recipe;
import com.example.tom.projeto3_udacity.R;

/**
 * Created by Tom on 27/04/2018.
 */

public class MasterListFragment extends Fragment implements StepsAdapter.StepsAdapterOnClickHandler{

    private Recipe recipe;
    private TextView tv_description;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private StepsAdapter mStepsAdapter;
    private View rootView;
    private OnStepSelectedListener mSelectedStepListener;


    public MasterListFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_master_list, container, false);
        Intent i = getActivity().getIntent();
        Bundle b = i.getBundleExtra("Receita");
        recipe = b.getParcelable("Receita");
        tv_description = (TextView) rootView.findViewById(R.id.tv_description);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_steps);
        layoutManager = new LinearLayoutManager(rootView.getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mStepsAdapter = new StepsAdapter(this, rootView.getContext());
        mRecyclerView.setAdapter(mStepsAdapter);
        mStepsAdapter.setstepList(recipe.getSteps());
        ingredientsToTextView();
        return rootView;
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

        mSelectedStepListener.onStepSelected(position);

    }
    public interface OnStepSelectedListener{
        void onStepSelected(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            mSelectedStepListener = (OnStepSelectedListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "interface not implemented OnStepSelectedListener");
        }

    }
}
