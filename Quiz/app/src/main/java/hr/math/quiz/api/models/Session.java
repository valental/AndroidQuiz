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

    @SerializedName("history_art")
    int lvlHistoryAndArt;

    @SerializedName("sport")
    int lvlSport;

    @SerializedName("science")
    int lvlScience;

    @SerializedName("geography")
    int lvlGeography;

    @SerializedName("movie")
    int lvlMovie;

    public int getLvlHistoryAndArt() {
        return lvlHistoryAndArt;
    }

    public int getLvlSport() {
        return lvlSport;
    }

    public int getLvlScience() {
        return lvlScience;
    }

    public int getLvlGeography() {
        return lvlGeography;
    }

    public int getLvlMovie() {
        return lvlMovie;
    }

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
