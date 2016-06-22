package com.byteshaft.hazardperceptiontest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.byteshaft.hazardperceptiontest.utils.AppGlobals;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;


public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView resultTextView;
    private Button goToStart;
    private boolean oneWrongAnswer = false;
    private int rightAnswer = 0;
    private TextView calculations;
    private ListView mListView;
    private ArrayList<String> questions;
    private HashMap<String, Boolean> answers;
    private ViewHolder viewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        mListView = (ListView) findViewById(R.id.list);
        setTitle("Test Result");
        resultTextView = (TextView) findViewById(R.id.result);
        calculations = (TextView) findViewById(R.id.calculation);
        goToStart = (Button) findViewById(R.id.go_to_start);
        goToStart.setOnClickListener(this);
        questions = getIntent()
                .getStringArrayListExtra(AppGlobals.QUESTIONS_ARRAY);
        answers = (HashMap<String, Boolean>)getIntent()
                .getSerializableExtra(AppGlobals.ANSWER_HASHMAP);
        Log.i("Total answer", String.valueOf(answers));
        Log.i("ques", String.valueOf(questions));
        for (String question: questions) {
            if (!answers.get(question)) {
                oneWrongAnswer = true;
            } else {
                rightAnswer = rightAnswer + 1;
            }
        }
        calculations.setText(rightAnswer+"/7");
        if (oneWrongAnswer) {
            resultTextView.setText("Fail");
            resultTextView.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        } else {
            resultTextView.setText("Pass");
        }
        Adapter adapter = new Adapter(getApplicationContext(), R.layout.question_delegate,
                questions, answers);
        mListView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.go_to_start:
                finish();
                break;
        }
    }

    class Adapter extends ArrayAdapter<String> {

        private ArrayList<String> que;
        private HashMap<String, Boolean> answers;

        public Adapter(Context context, int resource, ArrayList<String> que, HashMap<String, Boolean> answers) {
            super(context, resource);
            this.que = que;
            this.answers = answers;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = getLayoutInflater();
                convertView = inflater.inflate(R.layout.question_delegate, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.textView = (TextView) convertView.findViewById(R.id.que);
                viewHolder.answer = (TextView) convertView.findViewById(R.id.que_answer);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText(que.get(position));
            viewHolder.answer.setText(String.valueOf(answers.get(que.get(position))));
            return convertView;
        }

        @Override
        public int getCount() {
            return que.size();
        }
    }

    class ViewHolder {
        TextView textView;
        TextView answer;
    }
}
