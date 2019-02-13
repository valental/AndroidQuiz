package hr.math.quiz.game.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import java.util.concurrent.atomic.AtomicBoolean;

import hr.math.quiz.LoginActivity;
import hr.math.quiz.MainActivity;
import hr.math.quiz.helpers.PreferencesManager;
import hr.math.quiz.R;
import hr.math.quiz.api.ApiRequest;
import hr.math.quiz.game.models.Game;
import hr.math.quiz.game.models.GameQuestion;

import static android.view.View.TEXT_ALIGNMENT_CENTER;

public class QuestionInputActivity extends AppCompatActivity {

    GameQuestion myQuestion;
    ProgressBar progressBar;
    Handler handler = new Handler();
    long start = System.currentTimeMillis();
    Game game;
    // Thread safe boolean
    AtomicBoolean done = new AtomicBoolean(false);
    EditText myEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_input);

        game = Game.getInstance();
        myQuestion = game.getCurrentQuestion();

        String[] textParts = myQuestion.text.split("_");

        if (textParts.length == 1) {
            if (myQuestion.text.startsWith("_")) {
                addEditText();
                splitAndAdd(textParts[0]);
            } else if (myQuestion.text.endsWith("_")) {
                splitAndAdd(textParts[0]);
                addEditText();
            }
        } else if (textParts.length == 2) {
            splitAndAdd(textParts[0]);
            addEditText();
            splitAndAdd(textParts[1]);
        }

        ProgressBarSetup();
    }

    private void addEditText() {
        FlexboxLayout flexboxLayout = findViewById(R.id.flexboxLayout);

        EditText editText = new EditText(getApplicationContext());
        FlexboxLayout.LayoutParams wrap =
                new FlexboxLayout.LayoutParams(
                        FlexboxLayout.LayoutParams.WRAP_CONTENT,
                        FlexboxLayout.LayoutParams.WRAP_CONTENT);
        editText.setLayoutParams(wrap);
        editText.setWidth(100);
        editText.setTextAlignment(TEXT_ALIGNMENT_CENTER);

        myEditText = editText;
        flexboxLayout.addView(editText);
    }

    private void splitAndAdd(String s) {
        String[] parts = s.split(" ");
        if (!parts[0].startsWith("."))
            addTextView(" ");
        for (int i = 0; i < parts.length; i++) {
            addTextView(parts[i]);
        }
    }

    private void addTextView(String s) {
        FlexboxLayout flexboxLayout = findViewById(R.id.flexboxLayout);
        s += " ";
        TextView textView = new TextView(getApplicationContext());
        textView.setText(s);
        FlexboxLayout.LayoutParams wrap =
                new FlexboxLayout.LayoutParams(
                        FlexboxLayout.LayoutParams.WRAP_CONTENT,
                        FlexboxLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(wrap);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 23);
        flexboxLayout.addView(textView);
    }

    private void OpenNextActivity() {
        GameQuestion question = game.getNextQuestion();
        Intent intent;
        if (question == null) {
            // no more questions
            intent = new Intent(this, GameFinishedActivity.class);
        } else {
            // next question
            switch (question.type) {
                case 1:
                    intent = new Intent(this, QuestionSelectActivity.class);
                    break;
                case 2:
                    intent = new Intent(this, QuestionDropdownActivity.class);
                    //intent = new Intent(this, QuestionInputActivity.class);
                    break;
                case 3:
                    intent = new Intent(this, QuestionInputActivity.class);
                    //intent = new Intent(this, QuestionDropdownActivity.class);
                    break;
                default:
                    return;
            }
        }
        finish();
        startActivity(intent);
    }

    private void ProgressBarSetup() {
        progressBar = findViewById(R.id.questionProgressBar);
        progressBar.setMax(10000);

        //---do some work in background thread---
        new Thread(new Runnable() {
            public void run() {
                long progressTime = System.currentTimeMillis() - start;
                //-do some work here-
                while (progressTime < 10000) {
                    doSomeWork();

                    //-Update the progress bar-
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(10000 + (int) (start - System.currentTimeMillis()));
                        }
                    });
                    progressTime = System.currentTimeMillis() - start;
                }

                //---hides the progress bar---
                handler.post(new Runnable() {
                    public void run() {
                        if (done.compareAndSet(false, true)) {
                            // set the incorrect answer
                            game.setNextAnswer(-1);
                            game.addTime(10000);
                            OpenNextActivity();
                        }
                    }
                });
            }

            //---do some long running work here---
            private void doSomeWork() {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (menu instanceof MenuBuilder) {
            ((MenuBuilder) menu).setOptionalIconsVisible(true);
        }
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.general_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (done.compareAndSet(false, true)) {
            switch (item.getItemId()) {
                case R.id.logout:
                    PreferencesManager preferencesManager = new PreferencesManager(this);
                    ApiRequest.logoutUser(preferencesManager.loadSessionToken());
                    preferencesManager.ClearPreferences();
                    Intent intentLogin = new Intent(this, LoginActivity.class);
                    finish();
                    startActivity(intentLogin);
                    return true;
                case R.id.home:
                    Intent intentHome = new Intent(this, MainActivity.class);
                    startActivity(intentHome);
                    return true;
            }
        }
        return false;
    }

    public void submitBtnClick(View view) {
        if (done.compareAndSet(false, true)) {
            String userAnswer = myEditText.getText().toString();
            String correctAnswer = "";
            int correct = -1;
            switch (myQuestion.correct) {
                case 1:
                    correctAnswer = myQuestion.answer1;
                    correct = 1;
                    break;
                case 2:
                    correctAnswer = myQuestion.answer2;
                    correct = 2;
                    break;
                case 3:
                    correctAnswer = myQuestion.answer3;
                    correct = 3;
                    break;
                case 4:
                    correctAnswer = myQuestion.answer4;
                    correct = 4;
                    break;
            }
            if (userAnswer.toLowerCase().compareTo(correctAnswer.toLowerCase()) == 0) {
                game.setNextAnswer(correct);
            } else {
                game.setNextAnswer(-1);
            }
            game.addTime((int) (System.currentTimeMillis() - start));
            OpenNextActivity();
        }
    }
}
