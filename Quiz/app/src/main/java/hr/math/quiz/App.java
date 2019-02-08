package hr.math.quiz;

import android.app.Application;
import android.util.Log;

import hr.math.quiz.entities.MyObjectBox;
import hr.math.quiz.entities.Question;
import io.objectbox.Box;
import io.objectbox.BoxStore;

public class App extends Application {
    private BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        boxStore = MyObjectBox.builder().androidContext(this).build();
        populateDatabase();
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }

    private void populateDatabase() {
        Box<Question> questionBox = boxStore.boxFor(Question.class);

        Question question = new Question(1, 1, 3, 4, Question.Category.GEOGRAPHY);
        questionBox.put(question);

        Log.d("APP_TAG", "Inserted new question, ID: " + question.id);
    }
}
