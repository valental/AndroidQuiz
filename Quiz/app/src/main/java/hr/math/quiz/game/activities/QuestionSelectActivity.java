package hr.math.quiz.game.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicBoolean;

import hr.math.quiz.LoginActivity;
import hr.math.quiz.MainActivity;
import hr.math.quiz.helpers.PreferencesManager;
import hr.math.quiz.R;
import hr.math.quiz.api.ApiRequest;
import hr.math.quiz.game.models.Game;
import hr.math.quiz.game.models.GameQuestion;

public class QuestionSelectActivity extends AppCompatActivity {

    GameQuestion myQuestion;
    ProgressBar progressBar;
    Handler handler = new Handler();
    long start = System.currentTimeMillis();
    Game game;
    // Thread safe boolean
    AtomicBoolean done = new AtomicBoolean(false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_select);

        game = Game.getInstance();
        myQuestion = game.getCurrentQuestion();

        TextView textView = findViewById(R.id.questionTextView);
        Button btn1 = findViewById(R.id.answerBtn1);
        Button btn2 = findViewById(R.id.answerBtn2);
        Button btn3 = findViewById(R.id.answerBtn3);
        Button btn4 = findViewById(R.id.answerBtn4);

        textView.setText(myQuestion.text);
        btn1.setText(myQuestion.answer1);
        btn2.setText(myQuestion.answer2);
        btn3.setText(myQuestion.answer3);
        btn4.setText(myQuestion.answer4);

        ProgressBarSetup();
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

    public void answerOnClick(View view) {
        if (done.compareAndSet(false, true)) {
            switch (view.getId()) {
                case R.id.answerBtn1:
                    game.setNextAnswer(1);
                    break;
                case R.id.answerBtn2:
                    game.setNextAnswer(2);
                    break;
                case R.id.answerBtn3:
                    game.setNextAnswer(3);
                    break;
                case R.id.answerBtn4:
                    game.setNextAnswer(4);
                    break;
                default:
                    throw new RuntimeException("Unknow button ID");
            }
            game.addTime((int) (System.currentTimeMillis() - start));
            OpenNextActivity();
        }
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
}
