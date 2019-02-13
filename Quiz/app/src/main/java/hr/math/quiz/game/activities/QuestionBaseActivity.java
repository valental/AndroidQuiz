package hr.math.quiz.game.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;

import java.util.concurrent.atomic.AtomicBoolean;

import hr.math.quiz.LoginActivity;
import hr.math.quiz.MainActivity;
import hr.math.quiz.R;
import hr.math.quiz.api.ApiRequest;
import hr.math.quiz.game.models.Game;
import hr.math.quiz.game.models.GameQuestion;
import hr.math.quiz.helpers.PreferencesManager;

public class QuestionBaseActivity extends AppCompatActivity {
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

        game = Game.getInstance();
        myQuestion = game.getCurrentQuestion();

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

    protected void OpenNextActivity() {
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

    @SuppressLint("RestrictedApi")
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

    @Override
    public void onBackPressed() {
        done.set(true);
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        done.set(true);
        start = System.currentTimeMillis() - start;
        super.onStop();
    }
}
