package hr.math.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (R.id.logout == item.getItemId()) {
            PreferencesManager preferencesManager = new PreferencesManager(this);
            preferencesManager.ClearPreferences();
            Intent intent = new Intent(this, LoginActivity.class);
            finish();
            startActivity(intent);

            return true;
        }
        return false;
    }
}
