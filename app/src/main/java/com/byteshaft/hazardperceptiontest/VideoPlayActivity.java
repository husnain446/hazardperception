package com.byteshaft.hazardperceptiontest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;
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

    private long totalTime = 0;
    private long currentTime = 0;
    private int questionNumber = 0;
    private int stopPosition;

    private HashMap<String, Boolean> answersHashMap;

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
        answersHashMap = new HashMap<>();
        question = (TextView) findViewById(R.id.question);
        relativeLayout = (RelativeLayout) findViewById(R.id.question_layout);
        playClip = (Button) findViewById(R.id.start_film);
        playClip.setOnClickListener(this);
        videoLayout = (RelativeLayout) findViewById(R.id.video_layout);
        gestureTap = new GestureDetector(this, new GestureTap());
        loadQuestion(questionNumber);
    }

    private void loadQuestion(int value) {
        question.setText((questionNumber+1)+ ". " +questionsArray.get(value));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
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
        totalTime = mVideoView.getDuration();
        mediaPlayer.start();
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        long currentMin = Long.parseLong(answersData.get(questionsArray.get(questionNumber))[0]);
        if (currentMin == 10001) {
            answersHashMap.put(questionsArray.get(questionNumber), true);
        } else {
            answersHashMap.put(questionsArray.get(questionNumber), false);
        }
        if ((questionNumber + 1) < questionsArray.size()) {
            questionNumber = questionNumber + 1;
            loadQuestion(questionNumber);
            videoLayout.setVisibility(View.GONE);
            showLayoutAgain();
        } else {
            if (!AppGlobals.isEnabled()) {
                loadResultActivity();
            } else {
                finish();
            }
        }

    }


    private void loadResultActivity() {
        this.finish();
        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
        intent.putExtra(AppGlobals.QUESTIONS_ARRAY, questionsArray);
        intent.putExtra(AppGlobals.ANSWER_HASHMAP, answersHashMap);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_film:
                slideUp();
                break;
        }
    }


    private void showLayoutAgain() {
        Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_up);
        slideUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                relativeLayout.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        relativeLayout.startAnimation(slideUp);
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
            if (videoLayout.getVisibility() == View.VISIBLE) {
                currentTime = mVideoView.getCurrentPosition();
                mVideoView.pause();
                showDialog();
                return true;
            } else {
                return false;
            }
        }
    }

    public void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                VideoPlayActivity.this);
        alertDialogBuilder
                .setTitle("Confirm Your Action")
                .setMessage("Did you touch the screen?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        long currentMin = Long.parseLong(answersData.get(questionsArray.get(questionNumber))[0]);
                        long currentMax = Long.parseLong(answersData.get(questionsArray.get(questionNumber))[1]);
                        if (currentMin != 10001) {
                            if (currentTime > currentMin && currentTime < currentMax) {
                                if (AppGlobals.isEnabled()) {
                                    Toast.makeText(VideoPlayActivity.this, "Right answer", Toast.LENGTH_SHORT).show();
                                }
                                answersHashMap.put(questionsArray.get(questionNumber), true);

                            } else {
                                if (AppGlobals.isEnabled()) {
                                    Toast.makeText(VideoPlayActivity.this, "Wrong answer", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    mVideoView.start();
                                    return;
                                }
                                answersHashMap.put(questionsArray.get(questionNumber), false);
                            }
                        } else {
                            answersHashMap.put(questionsArray.get(questionNumber), false);
                        }
                        if ((questionNumber + 1) < questionsArray.size()) {
                            questionNumber = questionNumber + 1;
                            loadQuestion(questionNumber);
                            videoLayout.setVisibility(View.GONE);
                            showLayoutAgain();
                        } else {
                            if (!AppGlobals.isEnabled()) {
                                loadResultActivity();
                            } else {
                                finish();
                            }

                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        mVideoView.start();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
