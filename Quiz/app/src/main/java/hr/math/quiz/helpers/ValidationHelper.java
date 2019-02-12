package hr.math.quiz.helpers;

import java.util.regex.Pattern;

public class ValidationHelper {

    public static Boolean ValidateUsername(String username) {
        return Pattern.matches("^[A-Za-z]*[A-Za-z0-9][A-Za-z0-9 _]*$", username);
    }

    public static Boolean ValidateEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static Boolean ValidatePassword(String password) {
        return Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", password);
    }
}
