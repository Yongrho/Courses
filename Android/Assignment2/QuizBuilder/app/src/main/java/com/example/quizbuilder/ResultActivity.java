package com.example.quizbuilder;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    public static final String TAG_RESULT = "ResultActivity";

    String userName = null;
    int totalQuestion = 0;
    int countCorrect = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView tvUsername = findViewById(R.id.user_name);
        TextView tvCountQuestion = findViewById(R.id.questionCount);
        TextView tvMessage = findViewById(R.id.message);

        Button btnTryAgain = findViewById(R.id.btnAgain);
        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), QuizActivity.class);
                intent.putExtra("user_name", userName);
                startActivity(intent);
            }
        }); // end of anonymous inner class

        Button btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        }); // end of anonymous inner class

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                userName = extras.getString("user_name");
                tvUsername.setText(userName);

                totalQuestion = extras.getInt("total_question");
                countCorrect = extras.getInt("count_correct");
            }
        }

        float fCountCorrect = countCorrect;
        int countPercent = (int) (fCountCorrect / totalQuestion * 100);
        String strCountQuestion = getString(R.string.countResult, countCorrect, totalQuestion, countPercent);
        tvCountQuestion.setText(strCountQuestion);

        if (countPercent >= 90) {
            tvMessage.setText(getString(R.string.above90));
        } else {
            tvMessage.setText(getString(R.string.below90));
        }
    }
}