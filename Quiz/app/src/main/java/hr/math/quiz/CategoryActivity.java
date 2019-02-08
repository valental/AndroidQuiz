package hr.math.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import hr.math.quiz.entities.Progress;
import hr.math.quiz.entities.Question;
import io.objectbox.Box;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
    }

    public void FilmBtnClick(View view) {
        StartGame(Question.Category.MOVIE);
    }

    public void GeographyBtnClick(View view) {
        StartGame(Question.Category.GEOGRAPHY);
    }

    public void HistoryAndArtBtnClick(View view) {
        StartGame(Question.Category.HISTORY_ART);
    }

    public void SportBtnClick(View view) {
        StartGame(Question.Category.SPORT);
    }

    public void ScienceBtnClick(View view) {
        StartGame(Question.Category.SCIENCE);
    }

    private void StartGame(Question.Category category) {
        Game game = Game.getInstance();
        PreferencesManager preferencesManager = new PreferencesManager(getApplicationContext());
        String username = preferencesManager.LoadUsername();

        Box<Progress> progressBox = ((App)getApplication()).getBoxStore().boxFor(Progress.class);
        //progressBox.

        //game.setQuestions();

        //Intent in = new Intent(this, QuestionSelectActivity.class);
        //startActivity(in);
    }
}
