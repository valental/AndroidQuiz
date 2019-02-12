package hr.math.quiz.game.models;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

import hr.math.quiz.entities.Question;

public class Game {
    private static Game INSTANCE;
    private int currentQuestion;
    private List<GameQuestion> questions;
    private List<Integer> answersInt;
    private List<String> answersString;
    private int time;
    private int level;
    private Question.Category category;

    private Game() {
    }

    public static Game getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Game();
        }
        return INSTANCE;
    }

    public void setQuestions(List<GameQuestion> questions) {
        this.questions = questions;
        answersInt = new ArrayList<Integer>();
        answersString = new ArrayList<String>();
        currentQuestion = -1;
        time = 0;
    }

    public void setCategory(Question.Category category) {
        this.category = category;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setNextAnswer(int answer) {
        answersInt.add(answer);
        answersString.add("");
    }

    public void setNextAnswer(String answer) {
        answersInt.add(0);
        answersString.add(answer);
    }

    public GameQuestion getNextQuestion() {
        currentQuestion++;
        return currentQuestion < questions.size() ? questions.get(currentQuestion) : null;
    }

    public GameQuestion getCurrentQuestion() {
        return (0 <= currentQuestion && currentQuestion < questions.size()) ? questions.get(currentQuestion) : null;
    }

    public int numberOfCorrectAnswers() {
        int correct = 0;

        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).correct == answersInt.get(i)) {
                correct++;
            }
        }

        return correct;
    }

    public List<Pair<String, Boolean>> getReport() {
        List<Pair<String, Boolean>> report = new ArrayList<Pair<String, Boolean>>();
        for (int i = 0; i < questions.size(); i++) {
            report.add(new Pair<String, Boolean>(questions.get(i).text, questions.get(i).correct == answersInt.get(i)));
        }
        return report;
    }

    public boolean allCorrect() {
        return numberOfCorrectAnswers() == questions.size();
    }

    public int getTime() {
        return time;
    }

    public void addTime(int time) {
        this.time += time;
    }

    public int getLevel() {
        return level;
    }

    public Question.Category getCategory() {
        return category;
    }
}
