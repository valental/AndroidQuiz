package hr.math.quiz;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import hr.math.quiz.api.ApiRequest;
import hr.math.quiz.api.models.Session;
import hr.math.quiz.entities.Question;
import hr.math.quiz.helpers.PreferencesManager;
import hr.math.quiz.helpers.ValidationHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void Login(View view) {
        EditText usernameET = findViewById(R.id.usernameEditText);
        EditText passwordET = findViewById(R.id.passwordEditText);

        String username = usernameET.getText().toString();
        String password = passwordET.getText().toString();

        boolean validationSuccessful = true;

        int colorFailed = ContextCompat.getColor(getApplicationContext(), R.color.colorFailedValidation);

        if (ValidationHelper.ValidateUsername(username)) {
            usernameET.setBackgroundColor(Color.TRANSPARENT);
        } else {
            String string = getResources().getString(R.string.usernameError);
            ForegroundColorSpan fgcspan = new ForegroundColorSpan(colorFailed);
            SpannableStringBuilder ssbuilder = new SpannableStringBuilder(string);
            ssbuilder.setSpan(fgcspan, 0, string.length(), 0);

            usernameET.requestFocus();
            usernameET.setError(ssbuilder);
            validationSuccessful = false;
            /*
            usernameET.setBackgroundColor(colorFailed);
            MakeToastShort(R.string.usernameError);
            validationSuccessful = false;*/
        }

        if (ValidationHelper.ValidatePassword(password)) {
            passwordET.setBackgroundColor(Color.TRANSPARENT);
        } else {
            String string = getResources().getString(R.string.passwordError);
            ForegroundColorSpan fgcspan = new ForegroundColorSpan(colorFailed);
            SpannableStringBuilder ssbuilder = new SpannableStringBuilder(string);
            ssbuilder.setSpan(fgcspan, 0, string.length(), 0);

            passwordET.requestFocus();
            passwordET.setError(ssbuilder);
            validationSuccessful = false;

            /*
            passwordET.setBackgroundColor(colorFailed);
            MakeToastShort(R.string.passwordError);
            validationSuccessful = false;*/
        }

        if (!validationSuccessful) return;
        // Validation successful

        loginCall(username, password);
    }

    private void finishLogin(Session session) {
        PreferencesManager preferencesManager = new PreferencesManager(this);

        preferencesManager.SaveUsername(session.getUsername());
        preferencesManager.SaveSessionToken(session.getToken());
        preferencesManager.saveLevel(Question.Category.GEOGRAPHY, session.getLvlGeography());
        preferencesManager.saveLevel(Question.Category.HISTORY_ART, session.getLvlHistoryAndArt());
        preferencesManager.saveLevel(Question.Category.MOVIE, session.getLvlMovie());
        preferencesManager.saveLevel(Question.Category.SCIENCE, session.getLvlScience());
        preferencesManager.saveLevel(Question.Category.SPORT, session.getLvlSport());

        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
    }

    private void loginCall(final String username, String password) {
        Session session = new Session(username, password);

        Call<Session> sessionCall = ApiRequest.startSession(session);

        sessionCall.enqueue(new Callback<Session>() {
            @Override
            public void onResponse(Call<Session> call, Response<Session> response) {
                if(response.code() == 201) {
                    Session newSession = response.body();
                    finishLogin(newSession);
                } else if(response.code() == 422) {
                    MakeToastLong(getString(R.string.email_not_confirmed));
                } else if(response.code() == 400) {
                    MakeToastLong(getString(R.string.password_incorrect));
                } else {
                    MakeToastLong(getString(R.string.login_not_available));
                }
            }

            @Override
            public void onFailure(Call<Session> call, Throwable t) {
                MakeToastLong(getString(R.string.connection_not_established));
            }
        });
    }

    public void OpenRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void MakeToastShort(int id) {
        String s = getResources().getString(id);
        Toast toast = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    private void MakeToastLong(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
