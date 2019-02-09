package hr.math.quiz;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import hr.math.quiz.api.ApiRequest;
import hr.math.quiz.api.models.User;
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
        if (username != "") {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void OpenLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void Register(View view) {
        EditText usernameET = (EditText) findViewById(R.id.usernameEditText);
        EditText emailET = (EditText) findViewById(R.id.emailEditText);
        EditText passwordET = (EditText) findViewById(R.id.passwordEditText);
        EditText confirmPassET = (EditText) findViewById(R.id.confirmPasswordEditText);

        String username = usernameET.getText().toString();
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();
        String confirmPass = confirmPassET.getText().toString();
        Boolean validationSucceful = true;
        ValidationHelper validator = new ValidationHelper();
        int colorFailed = ContextCompat.getColor(getApplicationContext(), R.color.colorFailedValidation);

        if (validator.ValidateUsername(username)) {
            usernameET.setBackgroundColor(Color.TRANSPARENT);
        } else {
            usernameET.setBackgroundColor(colorFailed);
            MakeToastShort(R.string.usernameError);
            validationSucceful = false;
        }

        if (validator.ValidateEmail(email)) {
            emailET.setBackgroundColor(Color.TRANSPARENT);
        } else {
            emailET.setBackgroundColor(colorFailed);
            MakeToastShort(R.string.emailError);
            validationSucceful = false;
        }

        if (validator.ValidatePassword(password)) {
            passwordET.setBackgroundColor(Color.TRANSPARENT);
        } else {
            passwordET.setBackgroundColor(colorFailed);
            MakeToastShort(R.string.passwordError);
            validationSucceful = false;
        }

        if (password.equals(confirmPass)) {
            confirmPassET.setBackgroundColor(Color.TRANSPARENT);
        } else {
            confirmPassET.setBackgroundColor(colorFailed);
            MakeToastShort(R.string.confirmPassError);
            validationSucceful = false;
        }

        if (validationSucceful == false) return;
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
                    User newUser = response.body();
                    finishRegistration();
                } else {
                    try {
                        JSONObject error = new JSONObject(response.errorBody().string());
                        JSONObject errors = error.getJSONObject("errors");
                        MakeToastLong(errors.names().join(", ") + getString(R.string.already_taken));
                    } catch (Exception e) {
                        MakeToastLong(e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                MakeToastLong(getString(R.string.connection_not_established));
            }
        });
    }
}
