package hr.math.quiz;

import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        // TODO query progress from the db
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
}
