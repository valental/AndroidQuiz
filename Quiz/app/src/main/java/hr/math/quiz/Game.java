package hr.math.quiz;

import java.util.ArrayList;
import java.util.List;

import hr.math.quiz.entities.Question;

public class Game {
    private static Game INSTANCE;
    private int currentQuestion;
    private List<Question> questions;
    private List<Integer> answersInt;
    private List<String> answersString;

    private Game() {
    }

    public static Game getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Game();
        }
        return INSTANCE;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
        answersInt = new ArrayList<Integer>();
        currentQuestion = -1;
    }

    public void setNextAnswer(int answer) {
        answersInt.add(answer);
        answersString.add("");
    }

    public void setNextAnswer(String answer) {
        answersInt.add(0);
        answersString.add(answer);
    }

    public Question getNextQuestion() {
        currentQuestion++;
        return currentQuestion < questions.size() ? questions.get(currentQuestion) : null;
    }
}
