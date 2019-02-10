package hr.math.quiz.api.models;

import com.google.gson.annotations.SerializedName;

import hr.math.quiz.entities.Question;

public class Result {
    @SerializedName("category")
    String category;

    @SerializedName("lvl")
    int lvl;

    @SerializedName("time")
    double time;

    public Result(Question.Category category, int lvl, double time) {
        this.category = category.name().toLowerCase();
        this.lvl = lvl;
        this.time = time;
    }
}
