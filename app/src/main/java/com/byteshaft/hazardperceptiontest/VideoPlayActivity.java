package com.byteshaft.hazardperceptiontest;

import android.app.Activity;
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
        question = (TextView) findViewById(R.id.question);
        relativeLayout = (RelativeLayout) findViewById(R.id.question_layout);
        playClip = (Button) findViewById(R.id.start_film);
        playClip.setOnClickListener(this);
        videoLayout = (RelativeLayout) findViewById(R.id.video_layout);
        gestureTap = new GestureDetector(this, new GestureTap());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureTap.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private void playVideo() {
        mVideoView = (VideoView) findViewById(R.id.videoView);
        mVideoView.setOnPreparedListener(this);
        mVideoView.setOnCompletionListener(this);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.question_1;
        Log.i("TAG", "path " + path);
        mVideoView.setVideoURI(Uri.parse(path));
        mVideoView.seekTo(100);
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
//                showVideoView();
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
            return true;
        }
    }
}
