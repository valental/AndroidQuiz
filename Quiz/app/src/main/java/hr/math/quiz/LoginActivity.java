package hr.math.quiz;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

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
        Boolean validationSucceful = true;
        ValidationHelper validator = new ValidationHelper();
        int colorFailed = ContextCompat.getColor(getApplicationContext(), R.color.colorFailedValidation);

        if (validator.ValidateUsername(username)) {
            usernameET.setBackgroundColor(Color.TRANSPARENT);
        } else {
            String string = getResources().getString(R.string.usernameError);
            ForegroundColorSpan fgcspan = new ForegroundColorSpan(colorFailed);
            SpannableStringBuilder ssbuilder = new SpannableStringBuilder(string);
            ssbuilder.setSpan(fgcspan, 0, string.length(), 0);

            usernameET.requestFocus();
            usernameET.setError(ssbuilder);
            validationSucceful = false;
            /*
            usernameET.setBackgroundColor(colorFailed);
            MakeToastShort(R.string.usernameError);
            validationSucceful = false;*/
        }

        if (validator.ValidatePassword(password)) {
            passwordET.setBackgroundColor(Color.TRANSPARENT);
        } else {
            String string = getResources().getString(R.string.passwordError);
            ForegroundColorSpan fgcspan = new ForegroundColorSpan(colorFailed);
            SpannableStringBuilder ssbuilder = new SpannableStringBuilder(string);
            ssbuilder.setSpan(fgcspan, 0, string.length(), 0);

            passwordET.requestFocus();
            passwordET.setError(ssbuilder);
            validationSucceful = false;

            /*
            passwordET.setBackgroundColor(colorFailed);
            MakeToastShort(R.string.passwordError);
            validationSucceful = false;*/
        }

        if (validationSucceful == false) return;
        // Validation successful

        Boolean loginSuccessful = true;

        // TODO make a request to the server

        if (loginSuccessful) {
            PreferencesManager preferencesManager = new PreferencesManager(this);
            preferencesManager.SaveUsername(username);
            Intent intent = new Intent(this, MainActivity.class);
            finish();
            startActivity(intent);
        }
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
}
