package hr.math.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import hr.math.quiz.api.ApiRequest;
import hr.math.quiz.api.models.LeaderBoard;
import hr.math.quiz.helpers.PreferencesManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllScoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_scores);

        getScoresCall();
    }

    private void getScoresCall() {
        Call<LeaderBoard> scoresCall = ApiRequest.getScores();

        scoresCall.enqueue(new Callback<LeaderBoard>() {
            @Override
            public void onResponse(Call<LeaderBoard> call, Response<LeaderBoard> response) {
                if(response.code() == 200) {
                    LeaderBoard leaderBoard = response.body();
                    LoadScores(leaderBoard);
                } else {
                    MakeToastLong(getString(R.string.scores_not_available));
                }
            }

            @Override
            public void onFailure(Call<LeaderBoard> call, Throwable t) {
                MakeToastLong(getString(R.string.connection_not_established));
            }
        });
    }

    private void LoadScores(LeaderBoard leaderBoard) {
        String[] userMovies = {leaderBoard.getMovie1(), leaderBoard.getMovie2(), leaderBoard.getMovie3()};
        String[] userSport = {leaderBoard.getSport1(), leaderBoard.getSport2(), leaderBoard.getSport3()};
        String[] userScience = {leaderBoard.getScience1(), leaderBoard.getScience2(), leaderBoard.getScience3()};
        String[] userGeography = {leaderBoard.getGeography1(), leaderBoard.getGeography2(), leaderBoard.getGeography3()};
        String[] userHistoryAndArt = {leaderBoard.getHistoryAndArt1(), leaderBoard.getHistoryAndArt2(), leaderBoard.getHistoryAndArt3()};

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
            position.setTextSize(TypedValue.COMPLEX_UNIT_SP, 23);
            linearLayout.addView(position);

            TextView player = new TextView(this);
            player.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            player.setText(players[i]);
            player.setTextSize(TypedValue.COMPLEX_UNIT_SP, 23);
            linearLayout.addView(player);

            innerContainer.addView(linearLayout);
        }
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
        return false;
    }

    private void MakeToastLong(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
