package hr.math.quiz.entities;

import io.objectbox.annotation.Convert;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Progress {
    @Id public long id;

    public String username;

    @Convert(converter = Question.CategoryConverter.class, dbType = Integer.class)
    public Question.Category category;

    public int currentLevel;

    public Progress() {
    }

    public Progress(long id, long userId, Question.Category category, int currentLevel) {
        this.id = id;
        this.username = username;
        this.category = category;
        this.currentLevel = currentLevel;
    }
}
