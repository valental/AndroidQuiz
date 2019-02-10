package hr.math.quiz;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MyScoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_scores);

        LoadProgress();
    }

    private void LoadProgress() {
        String lvls = "/3";
        PreferencesManager preferencesManager = new PreferencesManager(getApplicationContext());
        String username = preferencesManager.LoadUsername();

        // TODO query progress from shared preferences
        int moviesLvl = 1;
        int sportLvl = 2;
        int scienceLvl = 1;
        int geographyLvl = 2;
        int historyAndArtLvl = 2;

        ProgressBar moviesPB = findViewById(R.id.filmProgressBar);
        ProgressBar sportPB = findViewById(R.id.sportProgressBar);
        ProgressBar sciencePB = findViewById(R.id.scienceProgressBar);
        ProgressBar geographyPB = findViewById(R.id.geographyProgressBar);
        ProgressBar historyAndArtPB = findViewById(R.id.historyAndArtProgressBar);

        moviesPB.setProgress(moviesLvl);
        sportPB.setProgress(sportLvl);
        sciencePB.setProgress(scienceLvl);
        geographyPB.setProgress(geographyLvl);
        historyAndArtPB.setProgress(historyAndArtLvl);

        TextView moviesPBText = findViewById(R.id.moviesProgressBarText);
        TextView sportPBText = findViewById(R.id.sportProgressBarText);
        TextView sciencePBText = findViewById(R.id.scienceProgressBarText);
        TextView geographyPBText = findViewById(R.id.geographyProgressBarText);
        TextView historyAndArtPBText = findViewById(R.id.historyAndArtProgressBarText);

        moviesPBText.setText(Integer.toString(moviesLvl) + lvls);
        sportPBText.setText(Integer.toString(sportLvl) + lvls);
        sciencePBText.setText(Integer.toString(scienceLvl) + lvls);
        geographyPBText.setText(Integer.toString(geographyLvl) + lvls);
        historyAndArtPBText.setText(Integer.toString(historyAndArtLvl) + lvls);

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
