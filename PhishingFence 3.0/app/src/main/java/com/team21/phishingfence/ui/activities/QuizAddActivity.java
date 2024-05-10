package com.team21.phishingfence.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.team21.phishingfence.R;
import com.team21.phishingfence.models.Quiz;
import com.team21.phishingfence.repositories.QuizRepository;
import com.team21.phishingfence.viewmodels.QuizViewModel;

public class QuizAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_add);

        Button button = findViewById(R.id.button4);
        QuizRepository quizRepository = new QuizRepository(this);

        Quiz quiz1 = new Quiz(7,
                QuizViewModel.BASIC_QUIZ,
                R.string.question7,
                7,
                true,
                R.string.warning7,
                R.string.tips7);

        Quiz quiz2 = new Quiz(8,
                QuizViewModel.BASIC_QUIZ,
                R.string.question8,
                8,
                true,
                R.string.warning8,
                R.string.tips8);

        Quiz quiz3 = new Quiz(9,
                QuizViewModel.BASIC_QUIZ,
                R.string.question9,
                9,
                true,
                R.string.warning9,
                R.string.tips9);

        Quiz quiz4 = new Quiz(10,
                QuizViewModel.BASIC_QUIZ,
                R.string.question10,
                10,
                true,
                R.string.warning10,
                R.string.tips10);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizRepository.deleteQuizzes(quiz1);
                quizRepository.insertQuizzes(quiz1);
            }
        });
    }
}