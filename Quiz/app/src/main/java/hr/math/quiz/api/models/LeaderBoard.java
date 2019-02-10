package hr.math.quiz.api.models;

import com.google.gson.annotations.SerializedName;

public class LeaderBoard {
    public String getSport1() {
        return sport1;
    }

    public String getSport2() {
        return sport2;
    }

    public String getSport3() {
        return sport3;
    }

    public String getHistoryAndArt1() {
        return historyAndArt1;
    }

    public String getHistoryAndArt2() {
        return historyAndArt2;
    }

    public String getHistoryAndArt3() {
        return historyAndArt3;
    }

    public String getScience1() {
        return science1;
    }

    public String getScience2() {
        return science2;
    }

    public String getScience3() {
        return science3;
    }

    public String getGeography1() {
        return geography1;
    }

    public String getGeography2() {
        return geography2;
    }

    public String getGeography3() {
        return geography3;
    }

    public String getMovie1() {
        return movie1;
    }

    public String getMovie2() {
        return movie2;
    }

    public String getMovie3() {
        return movie3;
    }

    @SerializedName("user_sport_1")
    String sport1;

    @SerializedName("user_sport_2")
    String sport2;

    @SerializedName("user_sport_3")
    String sport3;

    @SerializedName("user_history_art_1")
    String historyAndArt1;

    @SerializedName("user_history_art_2")
    String historyAndArt2;

    @SerializedName("user_history_art_3")
    String historyAndArt3;

    @SerializedName("user_science_1")
    String science1;

    @SerializedName("user_science_2")
    String science2;

    @SerializedName("user_science_3")
    String science3;

    @SerializedName("user_geography_1")
    String geography1;

    @SerializedName("user_geography_2")
    String geography2;

    @SerializedName("user_geography_3")
    String geography3;

    @SerializedName("user_movie_1")
    String movie1;

    @SerializedName("user_movie_2")
    String movie2;

    @SerializedName("user_movie_3")
    String movie3;
}
