package hr.math.quiz;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class PreferencesManager {
    private Context context;
    private int mode = MODE_PRIVATE;
    private String MYPREFS = "myPreferences";

    public PreferencesManager(Context context) {
        this.context = context;
    }

    public void SaveSessionToken(String token) {
        SavePreference("token", token);
    }

    public void SaveUsername(String username) {
        SavePreference("username", username);
    }

    public String LoadUsername() {
        return LoadPreference("username");
    }

    private String LoadPreference(String key){
        SharedPreferences mySharedPreferences=context.getSharedPreferences(MYPREFS,mode);
        return mySharedPreferences.getString(key, "");
    }

    private void SavePreference(String key, String value) {
        SharedPreferences mySharedPreferences=context.getSharedPreferences(MYPREFS, mode);
        SharedPreferences.Editor editor=mySharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
}
