package com.example.tom.projeto3_udacity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tom.projeto3_udacity.Model.Recipe;

import java.util.List;

/**
 * Created by Tom on 21/04/2018.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipesAdapterViewHolder>{

    private final RecipesAdapterOnClickHandler mClickHandler;
    private Context context;
    private List<Recipe> recipeList;


    public interface RecipesAdapterOnClickHandler{
        void onClick (Recipe recipe);
    }

    public RecipesAdapter(RecipesAdapterOnClickHandler clickHandler, Context context){
        mClickHandler = clickHandler;
        this.context = context;
    }

    public class RecipesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView tv_recipe;

        public RecipesAdapterViewHolder(View view){
            super(view);
            tv_recipe = (TextView) view.findViewById(R.id.tv_item_main);
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            int adapterPos = getAdapterPosition();
            mClickHandler.onClick(recipeList.get(adapterPos));
        }
    }

    @Override
    public RecipesAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int recipeItemLayout = R.layout.item_main_recyclerview;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(recipeItemLayout, parent, shouldAttachToParentImmediately);
        RecipesAdapterViewHolder holder = new RecipesAdapterViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecipesAdapterViewHolder holder, int position) {
        holder.tv_recipe.setText(recipeList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if(recipeList == null) return 0;
        return recipeList.size();
    }

    public void setRecipeList(List<Recipe> recipeList){
        this.recipeList = recipeList;
        notifyDataSetChanged();
    }
}
