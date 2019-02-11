package hr.math.quiz;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;

import java.util.concurrent.atomic.AtomicBoolean;

import hr.math.quiz.api.ApiRequest;
import hr.math.quiz.entities.GameQuestion;

public class QuestionInputActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_question_input);
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
