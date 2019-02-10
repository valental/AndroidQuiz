package hr.math.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class QuestionDropdownActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_dropdown);
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
        switch (item.getItemId()) {
            case R.id.logout:
                PreferencesManager preferencesManager = new PreferencesManager(this);
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
        return false;
    }
}
