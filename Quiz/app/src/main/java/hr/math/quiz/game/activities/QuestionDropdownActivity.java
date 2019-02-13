package hr.math.quiz.game.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.List;

import hr.math.quiz.R;

public class QuestionDropdownActivity extends QuestionBaseActivity implements AdapterView.OnItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_question_dropdown);
        super.onCreate(savedInstanceState);
        String[] textParts = myQuestion.text.split("_");
        if (textParts.length > 0) splitAndAdd(textParts[0]);
        addSpinner();
        if (textParts.length > 1) splitAndAdd(textParts[1]);
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

    private void addSpinner() {
        FlexboxLayout flexboxLayout = findViewById(R.id.flexboxLayout);
        List<String> myArraySpinner = new ArrayList<String>();

        myArraySpinner.add("");
        myArraySpinner.add(myQuestion.answer1);
        myArraySpinner.add(myQuestion.answer2);
        myArraySpinner.add(myQuestion.answer3);
        myArraySpinner.add(myQuestion.answer4);

        Spinner mySpinner = new Spinner(getApplicationContext());
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, myArraySpinner);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item); // The drop down view
        mySpinner.setAdapter(spinnerArrayAdapter);

        FlexboxLayout.LayoutParams wrap =
                new FlexboxLayout.LayoutParams(
                        FlexboxLayout.LayoutParams.WRAP_CONTENT,
                        FlexboxLayout.LayoutParams.WRAP_CONTENT);
        mySpinner.setLayoutParams(wrap);
        mySpinner.setBackgroundResource(R.drawable.spinner_background);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mySpinner.setPopupBackgroundResource(R.drawable.spinner_background);
        }
        mySpinner.setOnItemSelectedListener(this);
        flexboxLayout.addView(mySpinner);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        if (pos == 0)
            return;
        if (done.compareAndSet(false, true)) {
            game.setNextAnswer(pos);
            game.addTime((int) (System.currentTimeMillis() - start));
            OpenNextActivity();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
