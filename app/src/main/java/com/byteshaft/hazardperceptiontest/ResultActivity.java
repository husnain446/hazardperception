package com.byteshaft.hazardperceptiontest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.byteshaft.hazardperceptiontest.utils.AppGlobals;

import java.util.ArrayList;
import java.util.HashMap;


public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView resultTextView;
    private Button goToStart;
    private boolean oneWrongAnswer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setTitle("Test Result");
        resultTextView = (TextView) findViewById(R.id.result);
        goToStart = (Button) findViewById(R.id.go_to_start);
        goToStart.setOnClickListener(this);
        ArrayList<String> questions = getIntent()
                .getStringArrayListExtra(AppGlobals.QUESTIONS_ARRAY);
        HashMap<String, Boolean> answers = (HashMap<String, Boolean>)getIntent()
                .getSerializableExtra(AppGlobals.ANSWER_HASHMAP);
        Log.i("Total answer", String.valueOf(answers));
        Log.i("ques", String.valueOf(questions));
        for (String question: questions) {
            if (!answers.get(question)) {
                oneWrongAnswer = true;
            }
        }
        if (oneWrongAnswer) {
            resultTextView.setText("Fail");
            resultTextView.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        } else {
            resultTextView.setText("Pass");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.go_to_start:
                finish();
                break;
        }
    }
}
