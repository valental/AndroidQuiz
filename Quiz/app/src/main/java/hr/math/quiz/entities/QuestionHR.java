package hr.math.quiz.entities;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

@Entity
public class QuestionHR implements IQuestion {
    @Id(assignable = true) long id;

    public String text;

    public String answer1;
    public String answer2;
    public String answer3;
    public String answer4;

    public QuestionHR(long id, String text, String  answer1, String answer2, String answer3, String answer4)
    {
        this.id = id;
        this.text = text;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
    }

    public QuestionHR(){
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getAnswer1() {
        return answer1;
    }

    @Override
    public String getAnswer2() {
        return answer2;
    }

    @Override
    public String getAnswer3() {
        return answer3;
    }

    @Override
    public String getAnswer4() {
        return answer4;
    }
}
