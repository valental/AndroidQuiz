package hr.math.quiz.entities;

import io.objectbox.annotation.Convert;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.converter.PropertyConverter;
import io.objectbox.relation.ToOne;

@Entity
public class Question {
    @Id public long id;

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
        DEFAULT(0), GEOGRAPHY(1), SPORT(2), MUSIC(3), HISTORY(4);

        final int id;

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
}
