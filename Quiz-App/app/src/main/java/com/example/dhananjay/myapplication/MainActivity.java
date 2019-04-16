package com.example.dhananjay.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final int Q1_ANSWER = R.id.question_1_second_logo_radio;
    final int Q2_ANSWER = R.id.question_2_first_logo_radio;
    final String Q4_ANSWER = "marvin";
    final int Q5_ANSWER = R.id.question_5_third_radio;
    final String Q6_ANSWER = "42";
    final String Q7_ANSWER = "vogon";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkQuiz(View v) {
        ArrayList<String> incorrectAnswersList = new ArrayList<>();

        int numberOfQuestionsCorrect = 0;

        if( checkQuestion1() ){
            numberOfQuestionsCorrect++;
        } else {
            incorrectAnswersList.add("Question 1");
        }

        if( checkQuestion2() ){
            numberOfQuestionsCorrect++;
        } else {
            incorrectAnswersList.add("Question 2");
        }

        if( checkQuestion3() ){
            numberOfQuestionsCorrect++;
        } else {
            incorrectAnswersList.add("Question 3");
        }

        if( checkQuestion4() ){
            numberOfQuestionsCorrect++;
        } else {
            incorrectAnswersList.add("Question 4");
        }

        if( checkQuestion5() ){
            numberOfQuestionsCorrect++;
        } else {
            incorrectAnswersList.add("Question 5");
        }

        if( checkQuestion6() ){
            numberOfQuestionsCorrect++;
        } else {
            incorrectAnswersList.add("Question 6");
        }

        if( checkQuestion7() ){
            numberOfQuestionsCorrect++;
        } else {
            incorrectAnswersList.add("Question 7");
        }

        StringBuilder sb = new StringBuilder();
        for (String s : incorrectAnswersList)
        {
            sb.append(s);
            sb.append("\n");
        }

        Context context = getApplicationContext();
        CharSequence text = "Your score is " + numberOfQuestionsCorrect + "/7 \n\nRecheck the following:\n" + sb.toString();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private boolean checkQuestion1() {
        RadioGroup rg = findViewById(R.id.question_1_radio_group);

        return rg.getCheckedRadioButtonId() == Q1_ANSWER;

    }

    private boolean checkQuestion2() {
        RadioGroup rg = findViewById(R.id.question_2_radio_group);

        return rg.getCheckedRadioButtonId() == Q2_ANSWER;

    }

    private boolean checkQuestion3() {
        CheckBox c1 = findViewById(R.id.question_3_first_checkbox);
        CheckBox c2 = findViewById(R.id.question_3_second_checkbox);
        CheckBox c3 = findViewById(R.id.question_3_third_checkbox);

        return c1.isChecked() && c2.isChecked() && !c3.isChecked();

    }

    private boolean checkQuestion4() {
        EditText et = findViewById(R.id.question_4_edit_text);

        return et.getText().toString().equalsIgnoreCase(Q4_ANSWER);
    }

    private boolean checkQuestion5() {
        RadioGroup rg = findViewById(R.id.question_5_radio_group);

        return rg.getCheckedRadioButtonId() == Q5_ANSWER;

    }

    private boolean checkQuestion6() {
        EditText et = findViewById(R.id.question_6_edit_text);

        return et.getText().toString().equalsIgnoreCase(Q6_ANSWER);
    }

    private boolean checkQuestion7() {
        EditText et = findViewById(R.id.question_7_edit_text);

        return et.getText().toString().equalsIgnoreCase(Q7_ANSWER);
    }
}
