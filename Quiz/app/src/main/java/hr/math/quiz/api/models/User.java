package hr.math.quiz.api.models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    int id;

    @SerializedName("email")
    String email;

    @SerializedName("username")
    String username;

    @SerializedName("password")
    String password;

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
