package eeandrew.com.geoquiz;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import eeandrew.com.geoquiz.Question;

public class MainActivity extends ActionBarActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreButton;
    private ButtonListener btnListener;
    private TextView mQuestionView;
    private int questionIndex = -1;
    private Question questions[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateComponents();
        populateQuestions();
        addListener();
        showNextQuesion();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void populateComponents() {
        mTrueButton = (Button)findViewById(R.id.true_button);
        mFalseButton = (Button)findViewById(R.id.false_button);
        mNextButton = (Button) findViewById(R.id.next_btn);
        mQuestionView = (TextView) findViewById(R.id.question_view);
        mPreButton = (Button)findViewById(R.id.pre_btn);
    }

    private void addListener() {
        btnListener = new ButtonListener();
        mTrueButton.setOnClickListener(btnListener);
        mFalseButton.setOnClickListener(btnListener);
        mNextButton.setOnClickListener(btnListener);
        mPreButton.setOnClickListener(btnListener);
    }

    private void populateQuestions() {
        questions = new Question[] {
            new Question(R.string.question_01,true),
            new Question(R.string.question_02,false)
        };
    }

    private void showNextQuesion() {
        ++questionIndex;
        if(questionIndex >= questions.length){questionIndex = 0;}
        Question nextQuestion = questions[questionIndex];
        mQuestionView.setText(nextQuestion.getmQuestion());
    }

    private void showPreviousQuestion() {
        --questionIndex;
        if(questionIndex < 0) questionIndex = questions.length-1;
        Question nextQuestion = questions[questionIndex];
        mQuestionView.setText(nextQuestion.getmQuestion());
    }

    private void checkAnswer(boolean answer) {
        Question question = questions[questionIndex];
        if(question.ismAnswer() == answer){
            Toast.makeText(MainActivity.this,R.string.correct,Toast.LENGTH_SHORT).show();
            showNextQuesion();
        }else {
            Toast.makeText(MainActivity.this,R.string.wrong,Toast.LENGTH_SHORT).show();
        }
    }


    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int btnID = ((Button)v).getId();
            switch (btnID) {
                case R.id.true_button:
                    checkAnswer(true);
                    break;
                case R.id.false_button:
                    checkAnswer(false);
                    break;
                case R.id.next_btn:
                    showNextQuesion();
                    break;
                case R.id.pre_btn:
                    showNextQuesion();
                    break;
            }
        }
    }
}



