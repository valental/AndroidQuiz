package hr.math.quiz.api.models;

import com.google.gson.annotations.SerializedName;

public class Session {
    @SerializedName("id")
    int id;

    @SerializedName("username")
    String username;

    @SerializedName("password")
    String password;

    @SerializedName("token")
    String token;

    public Session(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return token;
    }

    public String getToken() {
        return token;
    }
}
