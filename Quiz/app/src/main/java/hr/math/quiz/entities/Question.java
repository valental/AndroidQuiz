package hr.math.quiz.entities;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import hr.math.quiz.App;
import hr.math.quiz.game.models.GameQuestion;
import io.objectbox.Box;
import io.objectbox.annotation.Convert;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.converter.PropertyConverter;
import io.objectbox.relation.ToOne;

@Entity
public class Question {
    @Id(assignable = true) public long id;

    public int level;
    public int type;
    public int correctAnswer;

    public long questionHRId;
    public long questionENId;

    public ToOne<QuestionHR> questionHR;
    public ToOne<QuestionEN> questionEN;

    @Convert(converter = CategoryConverter.class, dbType = Integer.class)
    public Category category;

    public Question(){
    }

    public Question(long id, int level, int type, int correctAnswer, Category category, long hrId, long enId) {
        this.id = id;
        this.level = level;
        this.type = type;
        this.correctAnswer = correctAnswer;
        this.category = category;
        this.questionHR.setTargetId(hrId);
        this.questionEN.setTargetId(enId);
    }

    public enum Category {
        DEFAULT(0), GEOGRAPHY(1), SPORT(2), MOVIE(3), HISTORY_ART(4), SCIENCE(5);

        public final int id;

        Category(int id) {
            this.id = id;
        }
    }

    public static class CategoryConverter implements PropertyConverter<Category, Integer> {
        @Override
        public Category convertToEntityProperty(Integer databaseValue) {
            if(databaseValue == null) {
                return null;
            }

            for(Category c : Category.values()) {
                if(c.id == databaseValue) {
                    return c;
                }
            }

            return Category.DEFAULT;
        }

        @Override
        public Integer convertToDatabaseValue(Category entityProperty) {
            return entityProperty == null ? null : entityProperty.id;
        }
    }

    public static List<GameQuestion> getQuestions(Application app, int level, Category category) {
        Box<Question> qBox = ((App)app).getBoxStore().boxFor(Question.class);
        Box<QuestionHR> qHRBox = ((App)app).getBoxStore().boxFor(QuestionHR.class);
        Box<QuestionEN> qENBox = ((App)app).getBoxStore().boxFor(QuestionEN.class);

        List<Question> questions = qBox.query().equal(Question_.level, level)
                                               .equal(Question_.category, category.id)
                                               .build().find();

        String language = Locale.getDefault().getDisplayLanguage();

        List<GameQuestion> rQuestions = new ArrayList<>();

        boolean isHR = language.toLowerCase().startsWith("hr") || language.toLowerCase().startsWith("cr");

        for(Question q : questions) {
            if(isHR) {
                rQuestions.add(new GameQuestion(qHRBox.get(q.questionHRId), q.correctAnswer, q.type));
            } else {
                rQuestions.add(new GameQuestion(qENBox.get(q.questionENId), q.correctAnswer, q.type));
            }
        }

        return rQuestions;
    }
}
