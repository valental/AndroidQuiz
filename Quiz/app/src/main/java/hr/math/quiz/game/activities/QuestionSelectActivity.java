package hr.math.quiz.game.activities;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
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

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import hr.math.quiz.LoginActivity;
import hr.math.quiz.MainActivity;
import hr.math.quiz.helpers.PreferencesManager;
import hr.math.quiz.R;
import hr.math.quiz.api.ApiRequest;
import hr.math.quiz.game.models.Game;
import hr.math.quiz.game.models.GameQuestion;

public class QuestionSelectActivity extends QuestionBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_question_select);
        super.onCreate(savedInstanceState);

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
                    throw new RuntimeException("Unknown button ID");
            }
            game.addTime((int) (System.currentTimeMillis() - start));
            OpenNextActivity();
        }
    }
}
