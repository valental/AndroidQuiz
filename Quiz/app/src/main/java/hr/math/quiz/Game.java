package hr.math.quiz;

import java.util.ArrayList;
import java.util.List;

import hr.math.quiz.entities.GameQuestion;
import hr.math.quiz.entities.Question;

public class Game {
    private static Game INSTANCE;
    private int currentQuestion;
    private List<GameQuestion> questions;
    private List<Integer> answersInt;
    private List<String> answersString;
    private int time;

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

    public int getTime() {
        return time;
    }

    public void addTime(int time) {
        this.time += time;
    }
}
