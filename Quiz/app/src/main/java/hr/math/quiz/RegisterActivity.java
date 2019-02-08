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

import java.util.regex.Pattern;

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
            MakeToast("Invalid username");
            validationSucceful = false;
        }

        if (validator.ValidateEmail(email)) {
            emailET.setBackgroundColor(Color.TRANSPARENT);
        } else {
            emailET.setBackgroundColor(colorFailed);
            MakeToast("Invalid email");
            validationSucceful = false;
        }

        if (validator.ValidatePassword(password)) {
            passwordET.setBackgroundColor(Color.TRANSPARENT);
        } else {
            passwordET.setBackgroundColor(colorFailed);
            MakeToast("Password must be at least 8 characters with at least 1 letter and 1 number.");
            validationSucceful = false;
        }

        if (password.equals(confirmPass)) {
            confirmPassET.setBackgroundColor(Color.TRANSPARENT);
        } else {
            confirmPassET.setBackgroundColor(colorFailed);
            MakeToast("Passwords do not match.");
            validationSucceful = false;
        }
        if (validationSucceful == false) return;

        // Validation successful


    }

    private void MakeToast(String s) {
        Toast toast = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
