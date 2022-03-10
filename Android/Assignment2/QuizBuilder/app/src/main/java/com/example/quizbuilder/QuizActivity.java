package com.example.quizbuilder;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class QuizActivity extends AppCompatActivity {
    public static final String TAG_QUIZ = "QuizActivity";
    public static final int MAX_BUTTONS = 4;

    HashMap<String, String> quizHashMap = new HashMap<>();
    ArrayList<String> termsList = new ArrayList<>();
    ArrayList<String> definitionsList = new ArrayList<>();
    ArrayList<String> burndownDefList;
    ArrayList<String> buttonsList = new ArrayList<>();

    TextView tvQuestionCount;
    TextView tvDefinition;
    Button btnButton1;
    Button btnButton2;
    Button btnButton3;
    Button btnButton4;
    Button btnNext;
    Button btnFinish;

    int correctCount = 0;
    int questionCount = 0;
    int totalQuestionCount = 0;

    String reply = null;
    String answer = null;
    String userName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userName = extras.getString("user_name");
        }

        tvQuestionCount = findViewById(R.id.questionCount);
        tvDefinition = findViewById(R.id.definition);

        loadButtons();

        loadQuizData();

        burndownDefList = new ArrayList<>(definitionsList);

        loadQuestion();
    }

    private void loadQuestion() {
        questionCount++;

        // update the count of questions
        totalQuestionCount = quizHashMap.size();
        String strQuestionCount = getString(R.string.countQuestion, questionCount, totalQuestionCount);
        tvQuestionCount.setText(strQuestionCount);

        Log.i(TAG_QUIZ, "remain: " + burndownDefList.size());
        if (burndownDefList.size() <= 1) {
            btnNext.setVisibility(View.GONE);
        }

        Collections.shuffle(burndownDefList);
        String definition = burndownDefList.get(0);
        burndownDefList.remove(0);

        tvDefinition.setText(definition);
        loadTerms(definition);
    }

    private void loadButtons() {
        btnButton1 = findViewById(R.id.button1);
        btnButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reply = btnButton1.getText().toString();
                updateEnableButton(false);
                updateButtonColor(0);
            }
        }); // end of anonymous inner class

        btnButton2 = findViewById(R.id.button2);
        btnButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reply = btnButton2.getText().toString();
                updateEnableButton(false);
                updateButtonColor(1);
            }
        }); // end of anonymous inner class

        btnButton3 = findViewById(R.id.button3);
        btnButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reply = btnButton3.getText().toString();
                updateEnableButton(false);
                updateButtonColor(2);
            }
        }); // end of anonymous inner class

        btnButton4 = findViewById(R.id.button4);
        btnButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reply = btnButton4.getText().toString();
                updateEnableButton(false);
                updateButtonColor(3);
            }
        }); // end of anonymous inner class

        btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEnableButton(true);
                updateButtonColor(-1);
                loadQuestion();
            }
        }); // end of anonymous inner class

        btnFinish = findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ResultActivity.class);
                intent.putExtra("user_name", userName);
                intent.putExtra("total_question", questionCount);
                intent.putExtra("count_correct", correctCount);
                startActivity(intent);
            }
        }); // end of anonymous inner class
    }

    private void loadTerms(String definition) {
        buttonsList.clear();

        // get the answer for the selected definition
        answer = quizHashMap.get(definition);
        buttonsList.add(answer);

        // choose random answers and make the answer list
        Collections.shuffle(termsList);
        for (String term : termsList) {
            if (!term.equals(answer)) {
                if (buttonsList.size() > MAX_BUTTONS - 1) {
                    break;
                }
                buttonsList.add(term);
            }
        }
        Collections.shuffle(buttonsList);

        btnButton1.setText(buttonsList.get(0));
        btnButton2.setText(buttonsList.get(1));
        btnButton3.setText(buttonsList.get(2));
        btnButton4.setText(buttonsList.get(3));
    }

    private void updateEnableButton(boolean next) {
        btnButton1.setEnabled(next);
        btnButton2.setEnabled(next);
        btnButton3.setEnabled(next);
        btnButton4.setEnabled(next);
    }

    @SuppressLint("NewApi")
    private void updateButtonColor(int btnIndex) {
        int color = getColor(R.color.purple_500);

        btnButton1.setBackgroundColor(getColor(R.color.gray));
        btnButton2.setBackgroundColor(getColor(R.color.gray));
        btnButton3.setBackgroundColor(getColor(R.color.gray));
        btnButton4.setBackgroundColor(getColor(R.color.gray));

        if (btnIndex != -1) {
            String message;
            if (answer.equals(reply)) {
                correctCount++;
                message = getString(R.string.msgCorrect);
            } else {
                color = getColor(R.color.red);
                message = getString(R.string.msgIncorrect);
            }
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }

        switch (btnIndex) {
            case 0:
                btnButton1.setBackgroundColor(color);
                break;
            case 1:
                btnButton2.setBackgroundColor(color);
                break;
            case 2:
                btnButton3.setBackgroundColor(color);
                break;
            case 3:
                btnButton4.setBackgroundColor(color);
                break;
            default:
                break;
        }
    }

    private void loadQuizData() {
        String line;
        BufferedReader br;
        try {
            InputStream is = getResources().openRawResource(R.raw.quiz_data);
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                String[] separated = line.split(":");
                definitionsList.add(separated[0]);
                termsList.add(separated[1]);
                quizHashMap.put(separated[0], separated[1]);
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.v(TAG_QUIZ, "There is an IOException.");
        } catch(Exception e){
            e.printStackTrace();
            Log.v(TAG_QUIZ, "There is an Exception.");
        }
    }
}