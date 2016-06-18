package com.byteshaft.hazardperceptiontest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.byteshaft.hazardperceptiontest.utils.AppGlobals;

import java.util.ArrayList;
import java.util.HashMap;

public class VideoPlayActivity extends Activity implements MediaPlayer.OnPreparedListener,
        MediaPlayer.OnCompletionListener, View.OnClickListener {

    private VideoView mVideoView;
    private TextView question;
    private Button playClip;
    private RelativeLayout relativeLayout;
    private static HashMap<String, String[]> answersData;
    private static ArrayList<String> questionsArray;
    private RelativeLayout videoLayout;
    private GestureDetector gestureTap;

    private int totalTime = 0;
    private int currentTime = 0;
    private int questionNumber = 0;

    public static void setUpQuestionsData(ArrayList<String> arrayList, HashMap<String,
            String[]> answers) {
        answersData = new HashMap<>();
        questionsArray = new ArrayList<>();
        answersData = answers;
        questionsArray = arrayList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test_activity);
        Data.initDataForTest();
        question = (TextView) findViewById(R.id.question);
        question.setText(questionsArray.get(questionNumber));
        relativeLayout = (RelativeLayout) findViewById(R.id.question_layout);
        playClip = (Button) findViewById(R.id.start_film);
        playClip.setOnClickListener(this);
        videoLayout = (RelativeLayout) findViewById(R.id.video_layout);
        gestureTap = new GestureDetector(this, new GestureTap());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureTap.onTouchEvent(event);
        if (mVideoView.isPlaying()) {
        }
        return super.onTouchEvent(event);
    }

    private void playVideo() {
        mVideoView = (VideoView) findViewById(R.id.videoView);
        mVideoView.setOnPreparedListener(this);
        mVideoView.setOnCompletionListener(this);
        String path = "android.resource://" + getPackageName() + "/" +
                getQuestion(answersData.get(questionsArray.get(questionNumber))[2]);
        Log.i("TAG", "path " + path);
        mVideoView.setVideoURI(Uri.parse(path));
        mVideoView.seekTo(100);
    }

    private int getQuestion(String imageName) {
        switch (imageName) {
            case "question_1":
                return R.raw.question_1;
            case "question_2":
                return R.raw.question_2;
            case "question_3":
                return R.raw.question_3;
            case "question_4":
                return R.raw.question_4;
            case "question_5":
                return R.raw.question_5;
            case "question_6":
                return R.raw.question_6;
            case "question_7":
                return R.raw.question_7;
        }
        return 0;
    }
    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();

    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_film:
                Log.i("TAG", "click");
                slideUp();
                showVideoView();
                break;
        }
    }

    private void slideUp() {

        Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_down);
        slideUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i("TAG", "ended");
                relativeLayout.setVisibility(View.GONE);
                showVideoView();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        relativeLayout.startAnimation(slideUp);
    }

    private void showVideoView() {
        Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_up);
        slideUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                videoLayout.setVisibility(View.VISIBLE);
                playVideo();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        videoLayout.startAnimation(slideUp);
    }

    class GestureTap extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.i("onDoubleTap :", "" + e.getAction());
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.i("onSingleTap :", "" + e.getAction());
            totalTime = mVideoView.getDuration();
            currentTime = mVideoView.getCurrentPosition();
            System.out.println(totalTime + "total time");
            System.out.println(currentTime + "current time");
            System.out.println(currentTime - totalTime + "pause time");
            showDialog();
            return true;
        }
    }

    public void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                VideoPlayActivity.this);
        alertDialogBuilder
                .setMessage("Are you sure?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        questionNumber++;
                        question.setText(questionsArray.get(questionNumber));
                        mVideoView.setVisibility(View.GONE);
                        relativeLayout.setVisibility(View.VISIBLE);
                        if (mVideoView.isPlaying()) {
                            relativeLayout.setVisibility(View.GONE);
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
