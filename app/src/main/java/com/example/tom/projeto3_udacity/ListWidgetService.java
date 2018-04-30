package com.example.tom.projeto3_udacity;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.tom.projeto3_udacity.Model.Ingredient;
import com.example.tom.projeto3_udacity.Model.Recipe;

import java.util.List;

/**
 * Created by Tom on 29/04/2018.
 */

public class ListWidgetService extends RemoteViewsService{

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListRemoteViewsFactory(this.getApplicationContext());
    }

    class ListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory{

        Context context;
        List<Recipe> recipes;

        public ListRemoteViewsFactory(Context context){
            this.context = context;
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {

        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            if(recipes != null) return recipes.size();
            return 0;
        }

        @Override
        public RemoteViews getViewAt(int i) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.item_widget);
            Recipe recipe = recipes.get(i);
            views.setTextViewText(R.id.tv_widget_item, recipe.getName());
            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 0;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }


}
