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

        Boolean registrationSuccessful = true;

        // TODO make a request to the server

        if (registrationSuccessful) {
            MakeToastLong(R.string.confirmRegistration);
            Intent intent = new Intent(this, LoginActivity.class);
            finish();
            startActivity(intent);
        }
    }

    private void MakeToastShort(int id) {
        String s = getResources().getString(id);
        Toast toast = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    private void MakeToastLong(int id) {
        String s = getResources().getString(id);
        Toast toast = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
