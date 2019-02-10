package hr.math.quiz;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ProgressBar;
import android.widget.Toast;

import hr.math.quiz.entities.Question;

public class QuestionSelectActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Handler handler = new Handler();
    long start = System.currentTimeMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_select);

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
                        OpenNextActivity();
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
    }
}
