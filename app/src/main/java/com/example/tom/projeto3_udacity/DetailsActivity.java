package com.example.tom.projeto3_udacity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.tom.projeto3_udacity.Model.Recipe;

/**
 * Created by Tom on 21/04/2018.
 */

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent i = getIntent();
        Bundle b = i.getBundleExtra("Receita");
        Recipe recipe = b.getParcelable("Receita");
        TextView tv = (TextView) findViewById(R.id.tv_details);


    }
}
