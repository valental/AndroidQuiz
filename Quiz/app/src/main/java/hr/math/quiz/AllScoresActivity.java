package hr.math.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AllScoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_scores);

        LoadScores();
    }

    private void LoadScores() {
        // TODO get the best player in each category
        String[] userMovies = {"Igrač1", "Igrač2", "Igrač3"};
        String[] userSport = {"Igrač4", "Igrač5", "Igrač6"};
        String[] userScience = {"Igrač7", "Igrač8", "Igrač9"};
        String[] userGeography = {"Igrač1", "Igrač2", "Igrač3"};
        String[] userHistoryAndArt = {"Igrač1", "Igrač2", "Igrač3"};

        AddScoreBoard(R.id.moviesPlayersLL, userMovies);
        AddScoreBoard(R.id.sportPlayersLL, userSport);
        AddScoreBoard(R.id.sciencePlayersLL, userScience);
        AddScoreBoard(R.id.geographyPlayersLL, userGeography);
        AddScoreBoard(R.id.historyAndArtPlayersLL, userHistoryAndArt);
    }

    private void AddScoreBoard(int parentLayoutId, String[] players){
        LinearLayout innerContainer = findViewById(parentLayoutId);
        for (int i = 0; i < players.length; i++) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            TextView position = new TextView(this);
            position.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
            position.setText(Integer.toString(i+1) + ". ");
            linearLayout.addView(position);

            TextView player = new TextView(this);
            player.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            player.setText(players[i]);
            linearLayout.addView(player);

            innerContainer.addView(linearLayout);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
