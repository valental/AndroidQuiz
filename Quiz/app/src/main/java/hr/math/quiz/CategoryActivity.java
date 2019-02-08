package hr.math.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
    }

    public void FilmBtnClick(View view) {
    }

    public void GeographyBtnClick(View view) {
    }

    public void HistoryAndArtBtnClick(View view) {
    }

    public void SportBtnClick(View view) {
        Intent in=new Intent(this, QuestionSelectActivity.class);
        startActivity(in);
    }

    public void ScienceBtnClick(View view) {
    }
}
