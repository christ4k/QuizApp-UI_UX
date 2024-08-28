package duth.ui_ux.quizapp;

import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView questionTextView;
    TextView totalQuestionsTextView;
    Button ansA, ansB, ansC, ansD;
    Button btn_submit;
    int score = 0;
    int totalQuestions = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer="";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalQuestionsTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_a);
        ansB = findViewById(R.id.ans_b);
        ansC = findViewById(R.id.ans_c);
        ansD = findViewById(R.id.ans_d);
        btn_submit = findViewById(R.id.btn_submit);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        btn_submit.setOnClickListener(this);

        totalQuestionsTextView.setText("Total questions: " + totalQuestions);
        loadNewQuestion();
    }

    private void resetColors(){
        ansA.setBackgroundColor(Color.parseColor("#64727D"));
        ansB.setBackgroundColor(Color.parseColor("#64727D"));
        ansC.setBackgroundColor(Color.parseColor("#64727D"));
        ansD.setBackgroundColor(Color.parseColor("#64727D"));
        btn_submit.setBackgroundColor(Color.parseColor("#000000"));
    }
    private void loadNewQuestion(){
        if(currentQuestionIndex == totalQuestions){
            finishQuiz();
            return;
        }

        resetColors();

        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);

        selectedAnswer = "";

    }

    private void finishQuiz(){
        String passStatus;
        // Condition for winning or losing
        if(score >= totalQuestions*0.6){
            passStatus = "Passed";
        }
        else {
            passStatus = "Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Your Score is " + score + " out of " + totalQuestions)
                .setPositiveButton("Restart",((dialog, i) -> restartQuiz()))
                .setCancelable(false)
                .show();
    }

    private void restartQuiz(){
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }

    @Override
    public void onClick(View view){
        Button clickedButton;
        clickedButton = (Button) view;

        if(clickedButton.getId() == R.id.btn_submit){
            if(!selectedAnswer.isEmpty()){
                if(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])){
                    score++;
                }
                currentQuestionIndex++;
                loadNewQuestion();
            }
            else {
                clickedButton.setBackgroundColor(Color.BLACK);
            }
        }
        else {
            selectedAnswer = clickedButton.getText().toString();
            resetColors();
            clickedButton.setBackgroundColor(Color.parseColor("#81C9EF"));
            btn_submit.setBackgroundColor(Color.parseColor("#9D162C"));
        }
    }
}
