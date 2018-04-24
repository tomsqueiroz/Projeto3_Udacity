package com.example.tom.projeto3_udacity.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tom.projeto3_udacity.Model.Step;
import com.example.tom.projeto3_udacity.R;

import java.util.List;

/**
 * Created by Tom on 21/04/2018.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsAdapterViewHolder>{

    private final StepsAdapterOnClickHandler mClickHandler;
    private Context context;
    private List<Step> stepList;


    public interface StepsAdapterOnClickHandler{
        void onClick(int position);
    }

    public StepsAdapter(StepsAdapterOnClickHandler clickHandler, Context context){
        mClickHandler = clickHandler;
        this.context = context;
    }

    public class StepsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView tv_descriptionStep;
        public final TextView tv_stepNumber;

        public StepsAdapterViewHolder(View view){
            super(view);
            tv_descriptionStep = (TextView) view.findViewById(R.id.tv_step_decription);
            tv_stepNumber = (TextView) view.findViewById(R.id.tv_step_number);
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            int adapterPos = getAdapterPosition();
            mClickHandler.onClick(adapterPos);
        }
    }

    @Override
    public StepsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int recipeItemLayout = R.layout.item_details_recyclerview;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(recipeItemLayout, parent, shouldAttachToParentImmediately);
        StepsAdapterViewHolder holder = new StepsAdapterViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(StepsAdapterViewHolder holder, int position) {
        holder.tv_stepNumber.setText(Integer.toString(position));
        holder.tv_descriptionStep.setText(stepList.get(position).getShortDescription());
    }

    @Override
    public int getItemCount() {
        if(stepList == null) return 0;
        return stepList.size();
    }

    public void setstepList(List<Step> stepList){
        this.stepList = stepList;
        notifyDataSetChanged();
    }
}
