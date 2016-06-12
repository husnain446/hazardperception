package com.byteshaft.hazardperceptiontest;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.VideoView;

public class TestActivity extends Activity implements MediaPlayer.OnPreparedListener,
        MediaPlayer.OnCompletionListener {

    private VideoView mVideoView;

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
