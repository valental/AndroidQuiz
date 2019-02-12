package hr.math.quiz.game.models;

import hr.math.quiz.entities.IQuestion;

public class GameQuestion {
    public String text;
    public String answer1;
    public String answer2;
    public String answer3;
    public String answer4;
    public int correct;
    public int type;

    public GameQuestion(IQuestion iq, int correct, int type) {
        this.text = iq.getText();
        this.answer1 = iq.getAnswer1();
        this.answer2 = iq.getAnswer2();
        this.answer3 = iq.getAnswer3();
        this.answer4 = iq.getAnswer4();
        this.correct = correct;
        this.type = type;
    }
}
