package com.byteshaft.hazardperceptiontest;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.HashMap;

public class TestActivity extends Activity implements MediaPlayer.OnPreparedListener,
        MediaPlayer.OnCompletionListener {

    private VideoView mVideoView;
    private static HashMap<String, String[]> answersData;
    private static ArrayList<String> questionsArray;

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
}
