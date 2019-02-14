package hr.math.quiz.game.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.util.Pair;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import hr.math.quiz.LoginActivity;
import hr.math.quiz.MainActivity;
import hr.math.quiz.R;
import hr.math.quiz.api.ApiRequest;
import hr.math.quiz.api.models.Result;
import hr.math.quiz.game.models.Game;
import hr.math.quiz.helpers.PreferencesManager;
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

        if (game.allCorrect()) {
            upgradeLevel(game);
        }

        LoadData();
    }

    private void upgradeLevel(Game game) {
        double time = ((double) game.getTime()) / 1000;

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
                if (response.code() == 200) {
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

    private void LoadData() {
        Game game = Game.getInstance();
        List<Pair<String, Boolean>> report = game.getReport();

        setTextViewText(R.id.row1,R.id.questionText1, report.get(0).first,toCorrect(report.get(0).second));
        setTextViewText(R.id.row2,R.id.questionText2, report.get(1).first,toCorrect(report.get(1).second));
        setTextViewText(R.id.row3,R.id.questionText3, report.get(2).first,toCorrect(report.get(2).second));
        setTextViewText(R.id.row4,R.id.questionText4, report.get(3).first,toCorrect(report.get(3).second));
        setTextViewText(R.id.row5,R.id.questionText5, report.get(4).first,toCorrect(report.get(4).second));
        setTextViewText(R.id.row6,R.id.questionText6, report.get(5).first,toCorrect(report.get(5).second));
        setTextViewText(R.id.row7,R.id.questionText7, report.get(6).first,toCorrect(report.get(6).second));
        setTextViewText(R.id.row8,R.id.questionText8, report.get(7).first,toCorrect(report.get(7).second));
        setTextViewText(R.id.row9,R.id.questionText9, report.get(8).first,toCorrect(report.get(8).second));
        setTextViewText(R.id.row10,R.id.questionText10, report.get(9).first,toCorrect(report.get(9).second));
/*
        setTextViewText(R.id.row1,R.id.correct1, toCorrect(report.get(0).second));
        setTextViewText(R.id.row2,R.id.correct2, toCorrect(report.get(1).second));
        setTextViewText(R.id.row3,R.id.correct3, toCorrect(report.get(2).second));
        setTextViewText(R.id.row4,R.id.correct4, toCorrect(report.get(3).second));
        setTextViewText(R.id.row5,R.id.correct5, toCorrect(report.get(4).second));
        setTextViewText(R.id.row6,R.id.correct6, toCorrect(report.get(5).second));
        setTextViewText(R.id.row7,R.id.correct7, toCorrect(report.get(6).second));
        setTextViewText(R.id.row8,R.id.correct8, toCorrect(report.get(7).second));
        setTextViewText(R.id.row9,R.id.correct9, toCorrect(report.get(8).second));
        setTextViewText(R.id.row10,R.id.correct10, toCorrect(report.get(9).second));*/
    }

    private void setTextViewText(int idrow,int id, String text, String result) {
        TextView textView = findViewById(id);
        textView.setText(text);
        TableRow tableRow=(TableRow) findViewById(idrow);
        if(result=="Correct")
            tableRow.setBackgroundColor(0XFF4DB857);
        else tableRow.setBackgroundColor(0XFFEF433F);
    }

    private String toCorrect(Boolean isCorrect) {
        return isCorrect ? "Correct" : "False";
    }
}
