package hr.math.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import hr.math.quiz.api.ApiRequest;
import hr.math.quiz.helpers.PreferencesManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (menu instanceof MenuBuilder) {
            ((MenuBuilder) menu).setOptionalIconsVisible(true);
        }
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
            ApiRequest.logoutUser(preferencesManager.loadSessionToken());
            preferencesManager.ClearPreferences();
            Intent intent = new Intent(this, LoginActivity.class);
            finish();
            startActivity(intent);
            
            return true;
        }
        return false;
    }

    public void PlayBtnClick(View view) {
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
    }

    public void MyScoresBtnClick(View view) {
        Intent intent = new Intent(this, MyScoresActivity.class);
        startActivity(intent);
    }

    public void AllScoresBtnClick(View view) {
        Intent intent = new Intent(this, AllScoresActivity.class);
        startActivity(intent);
    }
}
