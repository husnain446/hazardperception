package com.byteshaft.hazardperceptiontest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.byteshaft.hazardperceptiontest.utils.AppGlobals;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Welcome");
        continueButton = (Button) findViewById(R.id.button);
        continueButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                startActivity(new Intent(getApplicationContext(), VideoPlayActivity.class));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuItem = menu.findItem(R.id.enable_answer_mode);
        if (AppGlobals.isEnabled()) {
            menuItem.setTitle("Disable Instant Answer");
        } else {
            menuItem.setTitle("Enable Instant Answer");
        }
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.enable_answer_mode:
                if (AppGlobals.isEnabled()) {
                    AppGlobals.enable(false);
                    item.setTitle("Enable Instant Answer");
                } else {
                    AppGlobals.enable(true);
                    item.setTitle("Disable Instant Answer");
                }
                return true;
        }
        return false;
    }
}
