package com.example.tom.projeto3_udacity.Activities;

import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tom.projeto3_udacity.Model.Recipe;
import com.example.tom.projeto3_udacity.Model.Step;
import com.example.tom.projeto3_udacity.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;

/**
 * Created by Tom on 22/04/2018.
 */

public class StepDetailActivity extends AppCompatActivity{


    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mPlayerView;
    private TextView mDescriptionText;
    private ImageButton mNextStepButton;
    private ImageButton mPreviousStepButton;
    private List<Step> stepList;
    private Step step;
    private int position;
    private Recipe recipe;



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        mPlayerView = findViewById(R.id.playerView);
        mDescriptionText = findViewById(R.id.tv_step_detail);
        mNextStepButton =  findViewById(R.id.next_step);
        mPreviousStepButton = findViewById(R.id.previous_step);
        Intent intent = getIntent();

        if(savedInstanceState == null){
            recipe =  intent.getBundleExtra("Receita").getParcelable("Receita");
            stepList = recipe.getSteps();
            position = intent.getBundleExtra("Receita").getInt("Position");
        }else{
            recipe = savedInstanceState.getParcelable("Receita");
            stepList = recipe.getSteps();
            position = savedInstanceState.getInt("Position");
        }
        updateStep();

        //pegar url
        //fazer if para testar se tem video
    }
    public void updateStep(){

        if(position == 0) mPreviousStepButton.setVisibility(View.INVISIBLE);
        else if(position >= stepList.size()-1) mNextStepButton.setVisibility(View.INVISIBLE);
        else{
            mPreviousStepButton.setVisibility(View.VISIBLE);
            mNextStepButton.setVisibility(View.VISIBLE);
        }
        step = stepList.get(position);
        mDescriptionText.setText(step.getDescription());
        Uri mediaUri = Uri.parse(step.getVideoURL());
        initializePlayer(mediaUri);

    }

    private void initializePlayer(Uri mediaUri){
        if(mExoPlayer == null){
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);
            String userAgent = Util.getUserAgent(this, "BakingApp");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                    this, userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
        }
    }

    private void releasePlayer() {
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }

    @Override
    protected void onDestroy() {
        releasePlayer();
        super.onDestroy();
    }
    public void nextStepListener(View view){
        position ++;
        updateStep();
    }

    public void previousStepListener(View view){
        position--;
        updateStep();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("Receita", recipe);
        outState.putInt("Position", position);
    }
}
