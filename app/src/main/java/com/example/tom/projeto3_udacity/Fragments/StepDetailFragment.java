package com.example.tom.projeto3_udacity.Fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tom.projeto3_udacity.Model.Recipe;
import com.example.tom.projeto3_udacity.Model.Step;
import com.example.tom.projeto3_udacity.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;

/**
 * Created by Tom on 28/04/2018.
 */

public class StepDetailFragment extends Fragment {

    private View rootView;
    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mPlayerView;
    private TextView mDescriptionText;
    private ImageButton mNextStepButton;
    private ImageButton mPreviousStepButton;
    private List<Step> stepList;
    private Step step;
    private int position;
    private Recipe recipe;
    LayoutInflater inflater;
    ViewGroup container;


    public StepDetailFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.inflater = inflater;
        this.container = container;
        Intent intent = getActivity().getIntent();


        if (savedInstanceState == null) {
            recipe = intent.getBundleExtra("Receita").getParcelable("Receita");
            stepList = recipe.getSteps();
            position = intent.getBundleExtra("Receita").getInt("Position");
        } else {
            recipe = savedInstanceState.getParcelable("Receita");
            stepList = recipe.getSteps();
            position = savedInstanceState.getInt("Position");
        }
        if(getArguments()!= null){
            position = getArguments().getInt("Position");
        }

        rootView = inflater.inflate(R.layout.fragment_step_detail, container, false);
        mPlayerView = rootView.findViewById(R.id.playerView);
        mDescriptionText = rootView.findViewById(R.id.tv_step_detail);
        mNextStepButton = rootView.findViewById(R.id.next_step);
        mPreviousStepButton = rootView.findViewById(R.id.previous_step);
        updateStep();

        mNextStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position++;
                updateStep();
            }
        });

        mPreviousStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position--;
                updateStep();
            }
        });
        return rootView;
    }

    public void setPosition(int position) {
        this.position = position;
        updateStep();
    }

    public void updateStep() {

        if (position == 0) mPreviousStepButton.setVisibility(View.INVISIBLE);
        else if (position >= stepList.size() - 1) mNextStepButton.setVisibility(View.INVISIBLE);
        else {
            mPreviousStepButton.setVisibility(View.VISIBLE);
            mNextStepButton.setVisibility(View.VISIBLE);
        }
        step = stepList.get(position);
        mDescriptionText.setText(step.getDescription());
        Uri mediaUri = Uri.parse(step.getVideoURL());
        if (mExoPlayer != null) releasePlayer();
        initializePlayer(mediaUri);

    }

    private void initializePlayer(Uri mediaUri) {
        TrackSelector trackSelector = new DefaultTrackSelector();
        LoadControl loadControl = new DefaultLoadControl();
        mExoPlayer = ExoPlayerFactory.newSimpleInstance(rootView.getContext(), trackSelector, loadControl);
        mPlayerView.setPlayer(mExoPlayer);
        String userAgent = Util.getUserAgent(rootView.getContext(), "BakingApp");
        MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                rootView.getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
        mExoPlayer.prepare(mediaSource);
        mExoPlayer.setPlayWhenReady(true);
    }

    private void releasePlayer() {
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("Receita", recipe);
        outState.putInt("Position", position);
    }

    @Override
    public void onDestroy() {
        releasePlayer();
        super.onDestroy();
    }
}
