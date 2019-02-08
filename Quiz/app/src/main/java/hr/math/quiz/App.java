package hr.math.quiz;

import android.app.Application;
import android.util.Log;

import hr.math.quiz.entities.MyObjectBox;
import hr.math.quiz.entities.Question;
import hr.math.quiz.entities.QuestionEN;
import hr.math.quiz.entities.QuestionHR;
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
        Box<QuestionHR> questionHRBox = boxStore.boxFor(QuestionHR.class);
        Box<QuestionEN> questionENBox = boxStore.boxFor(QuestionEN.class);

        QuestionHR qhr = new QuestionHR(1, "TEXT pitanje 1", "a1", "a2", "a3", "a4");
        QuestionEN qen = new QuestionEN(1, "TEXT question 1", "a1", "a2", "a3", "a4");

        long hrId = questionHRBox.put(qhr);
        long enId = questionENBox.put(qen);

        Question question = new Question(1, 1, 3, 4, Question.Category.GEOGRAPHY, hrId, enId);

        questionBox.put(question);

        Log.d("APP_TAG", "Inserted new question, ID: " + question.id);

        qhr = new QuestionHR(2, "TEXT pitanje 2", "a1", "a2", "a3", "a4");
        qen = new QuestionEN(2, "TEXT question 2", "a1", "a2", "a3", "a4");

        hrId = questionHRBox.put(qhr);
        enId = questionENBox.put(qen);

        question = new Question(2, 1, 3, 2, Question.Category.HISTORY, hrId, enId);

        questionBox.put(question);

        Log.d("APP_TAG", "Inserted new question, ID: " + question.id);
        Log.d("APP_TAG", "Inserted new question, text: " + questionENBox.get(question.questionENId).text);
    }
}
