package hr.math.quiz.game.activities;

import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import hr.math.quiz.R;

import static android.view.View.TEXT_ALIGNMENT_CENTER;

public class QuestionInputActivity extends QuestionBaseActivity {
    EditText myEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_question_input);
        super.onCreate(savedInstanceState);

        String[] textParts = myQuestion.text.split("_");
        if (textParts.length == 1) {
            if (myQuestion.text.startsWith("_")) {
                addEditText();
                splitAndAdd(textParts[0]);
            } else if (myQuestion.text.endsWith("_")) {
                splitAndAdd(textParts[0]);
                addEditText();
            }
        } else if (textParts.length == 2) {
            splitAndAdd(textParts[0]);
            addEditText();
            splitAndAdd(textParts[1]);
        }
    }

    private void addEditText() {
        FlexboxLayout flexboxLayout = findViewById(R.id.flexboxLayout);

        EditText editText = new EditText(getApplicationContext());
        FlexboxLayout.LayoutParams wrap =
                new FlexboxLayout.LayoutParams(
                        FlexboxLayout.LayoutParams.WRAP_CONTENT,
                        FlexboxLayout.LayoutParams.WRAP_CONTENT);
        editText.setLayoutParams(wrap);
        editText.setWidth(100);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            editText.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        }

        myEditText = editText;
        flexboxLayout.addView(editText);
    }

    private void splitAndAdd(String s) {
        String[] parts = s.split(" ");
        if (!parts[0].startsWith("."))
            addTextView(" ");
        for (int i = 0; i < parts.length; i++) {
            addTextView(parts[i]);
        }
    }

    private void addTextView(String s) {
        FlexboxLayout flexboxLayout = findViewById(R.id.flexboxLayout);
        s += " ";
        TextView textView = new TextView(getApplicationContext());
        textView.setText(s);
        FlexboxLayout.LayoutParams wrap =
                new FlexboxLayout.LayoutParams(
                        FlexboxLayout.LayoutParams.WRAP_CONTENT,
                        FlexboxLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(wrap);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 23);
        flexboxLayout.addView(textView);
    }

    public void submitBtnClick(View view) {
        if (done.compareAndSet(false, true)) {
            String userAnswer = myEditText.getText().toString();
            String correctAnswer = "";
            int correct = -1;
            switch (myQuestion.correct) {
                case 1:
                    correctAnswer = myQuestion.answer1;
                    correct = 1;
                    break;
                case 2:
                    correctAnswer = myQuestion.answer2;
                    correct = 2;
                    break;
                case 3:
                    correctAnswer = myQuestion.answer3;
                    correct = 3;
                    break;
                case 4:
                    correctAnswer = myQuestion.answer4;
                    correct = 4;
                    break;
            }
            if (userAnswer.toLowerCase().compareTo(correctAnswer.toLowerCase()) == 0) {
                game.setNextAnswer(correct);
            } else {
                game.setNextAnswer(-1);
            }
            game.addTime((int) (System.currentTimeMillis() - start));
            OpenNextActivity();
        }
    }
}
