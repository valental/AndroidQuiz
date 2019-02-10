package hr.math.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import hr.math.quiz.api.ApiRequest;
import hr.math.quiz.api.models.LeaderBoard;
import hr.math.quiz.api.models.Result;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameFinishedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_finished);

        Game game = Game.getInstance();

        if(game.allCorrect()) {
            upgradeLevel(game);
        }

    }

    private void upgradeLevel(Game game) {
        double time = ( (double)game.getTime() ) / 1000;

        Result result = new Result(game.getCategory(), game.getLevel(), time);

        PreferencesManager preferencesManager = new PreferencesManager(this);
        String token = preferencesManager.loadSessionToken();

        getLevelCall(result, token);
    }

    private void getLevelCall(Result result, String Authorization) {
        Call<ResponseBody> levelCall = ApiRequest.upgradeLevel(result, Authorization);

        levelCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.code() == 200) {
                    MakeToastLong(getString(R.string.level_updated_successfully));
                    updateSharedPrefsLevel();
                } else {
                    MakeToastLong(getString(R.string.score_update_not_possible));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                MakeToastLong(getString(R.string.connection_not_established));
            }
        });
    }

    private void updateSharedPrefsLevel() {
        Game game = Game.getInstance();
        PreferencesManager preferencesManager = new PreferencesManager(this);

        preferencesManager.saveLevel(game.getCategory(), game.getLevel() + 1);
    }

    private void MakeToastLong(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
