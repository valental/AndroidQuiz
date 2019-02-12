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

import org.json.JSONObject;

import hr.math.quiz.api.ApiRequest;
import hr.math.quiz.api.models.User;
import hr.math.quiz.helpers.PreferencesManager;
import hr.math.quiz.helpers.ValidationHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        PreferencesManager preferencesManager = new PreferencesManager(this);
        String username = preferencesManager.LoadUsername();

        if (!username.isEmpty()) {
            Intent intent = new Intent(this, MainActivity.class);
            finish();
            startActivity(intent);
        }
    }

    public void OpenLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void Register(View view) {
        EditText usernameET = findViewById(R.id.usernameEditText);
        EditText emailET = findViewById(R.id.emailEditText);
        EditText passwordET = findViewById(R.id.passwordEditText);
        EditText confirmPassET = findViewById(R.id.confirmPasswordEditText);

        String username = usernameET.getText().toString();
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();
        String confirmPass = confirmPassET.getText().toString();
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

        if (ValidationHelper.ValidateEmail(email)) {
            emailET.setBackgroundColor(Color.TRANSPARENT);
        } else {
            String string = getResources().getString(R.string.emailError);
            ForegroundColorSpan fgcspan = new ForegroundColorSpan(colorFailed);
            SpannableStringBuilder ssbuilder = new SpannableStringBuilder(string);
            ssbuilder.setSpan(fgcspan, 0, string.length(), 0);

            emailET.requestFocus();
            emailET.setError(ssbuilder);
            validationSuccessful = false;
            /*
            emailET.setBackgroundColor(colorFailed);
            MakeToastShort(R.string.emailError);
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

        if (password.equals(confirmPass)) {
            confirmPassET.setBackgroundColor(Color.TRANSPARENT);
        } else {
            String string = getResources().getString(R.string.confirmPassError);
            ForegroundColorSpan fgcspan = new ForegroundColorSpan(colorFailed);
            SpannableStringBuilder ssbuilder = new SpannableStringBuilder(string);
            ssbuilder.setSpan(fgcspan, 0, string.length(), 0);

            confirmPassET.requestFocus();
            confirmPassET.setError(ssbuilder);
            validationSuccessful = false;
            /*
            confirmPassET.setBackgroundColor(colorFailed);
            MakeToastShort(R.string.confirmPassError);
            validationSuccessful = false;*/
        }

        if (!validationSuccessful) return;
        // Validation successful

        registrationCall(email, username, password);
    }

    private void finishRegistration() {
        MakeToastLong(R.string.confirmRegistration);
        Intent intent = new Intent(this, LoginActivity.class);
        finish();
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

    private void MakeToastLong(int id) {
        String s = getResources().getString(id);
        Toast toast = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    private void registrationCall(String email, String username, String password) {
        User user = new User(email, username, password);

        Call<User> createCall = ApiRequest.createUser(user);

        createCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code() == 201) {
                    finishRegistration();
                } else {
                    try {
                        JSONObject error = new JSONObject(response.errorBody().string());
                        JSONObject errors = error.getJSONObject("errors");
                        MakeToastLong(errors.names().join(", ") + getString(R.string.already_taken));
                    } catch (Exception e) {
                        MakeToastLong(R.string.registration_unavailable);
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                MakeToastLong(R.string.connection_not_established);
            }
        });
    }
}
