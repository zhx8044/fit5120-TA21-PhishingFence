package com.team21.phishingfence.ui.activities;

import static com.team21.phishingfence.ui.activities.MainActivity.FIRST_TIME_KEY;
import static com.team21.phishingfence.viewmodels.QuizViewModel.BASIC_QUIZ;
import static com.team21.phishingfence.viewmodels.QuizViewModel.EMAIL_QUIZ;
import static com.team21.phishingfence.viewmodels.QuizViewModel.MESSAGE_QUIZ;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.team21.phishingfence.R;
import com.team21.phishingfence.models.Quiz;
import com.team21.phishingfence.repositories.QuizRepository;

public class WelcomeActivity extends AppCompatActivity {
    QuizRepository quizRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ImageView cancel = findViewById(R.id.imageViewCancel);

        quizRepository = new QuizRepository(this);

        SharedPreferences settings = getSharedPreferences(MainActivity.PREFS_NAME, 0);
        boolean isFirstTime = settings.getBoolean(FIRST_TIME_KEY, true);
        if (isFirstTime) {
            updateQuiz();

            // 修改 firstTime 为 false
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean(FIRST_TIME_KEY, false);
            editor.apply();
        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void updateQuiz() {
        Quiz quiz1 = new Quiz(1,BASIC_QUIZ, R.string.question1,R.string.message14,true,R.string.warning1,R.string.tips1);
        Quiz quiz2 = new Quiz(2,BASIC_QUIZ, R.string.question2,R.string.message14,true,R.string.warning2,R.string.tips2);
        Quiz quiz3 = new Quiz(3,BASIC_QUIZ, R.string.question3,R.string.message14,true,R.string.warning3,R.string.tips3);
        Quiz quiz4 = new Quiz(4,BASIC_QUIZ, R.string.question4,R.string.message14,true,R.string.warning4,R.string.tips4);
        Quiz quiz5 = new Quiz(5,BASIC_QUIZ, R.string.question5,R.string.message14,true,R.string.warning5,R.string.tips5);
        Quiz quiz6 = new Quiz(6,BASIC_QUIZ, R.string.question6,R.string.message14,true,R.string.warning6,R.string.tips6);
        Quiz quiz7 = new Quiz(7,BASIC_QUIZ, R.string.question7,R.string.message14,true,R.string.warning7,R.string.tips7);
        Quiz quiz8 = new Quiz(8,BASIC_QUIZ, R.string.question8,R.string.message14,true,R.string.warning8,R.string.tips8);
        Quiz quiz9 = new Quiz(9,BASIC_QUIZ, R.string.question9,R.string.message14,true,R.string.warning9,R.string.tips9);
        Quiz quiz10 = new Quiz(10,BASIC_QUIZ, R.string.question10,R.string.message14,true,R.string.warning10,R.string.tips10);
        Quiz quiz11 = new Quiz(11,BASIC_QUIZ, R.string.question11,R.string.message14,true,R.string.warning11,R.string.tips11);
        Quiz quiz12 = new Quiz(12,BASIC_QUIZ, R.string.question12,R.string.message14,true,R.string.warning12,R.string.tips12);
        Quiz quiz13 = new Quiz(13,MESSAGE_QUIZ,R.string.question13,R.string.message13,true,R.string.warning13,R.string.tips13);
        Quiz quiz14 = new Quiz(14,EMAIL_QUIZ,R.string.message14,R.string.message14,true,R.string.warning14,R.string.tips14);
        Quiz quiz15 = new Quiz(15,EMAIL_QUIZ,R.string.message14,R.string.message15,true,R.string.warning15,R.string.tips15);
        Quiz quiz16 = new Quiz(16,EMAIL_QUIZ,R.string.message14,R.string.message16,false,R.string.warning16,R.string.tips16);
        Quiz quiz17 = new Quiz(17, BASIC_QUIZ, R.string.question17, R.string.message14, false, R.string.warning17, R.string.tips17);
        Quiz quiz18 = new Quiz(18, MESSAGE_QUIZ, R.string.question18, R.string.message18, false, R.string.warning18, R.string.tips18);
        Quiz quiz19 = new Quiz(19, BASIC_QUIZ, R.string.question19, R.string.message14, false, R.string.warning19, R.string.tips19);
        Quiz quiz20 = new Quiz(20, BASIC_QUIZ, R.string.question20, R.string.message14, false, R.string.warning20, R.string.tips20);
        Quiz quiz21 = new Quiz(21, EMAIL_QUIZ, R.string.message14, R.string.message21, false, R.string.warning21, R.string.tips21);
        Quiz quiz22 = new Quiz(22, EMAIL_QUIZ, R.string.message14, R.string.message22, false, R.string.warning22, R.string.tips22);
        Quiz quiz23 = new Quiz(23, EMAIL_QUIZ, R.string.message14, R.string.message23, false, R.string.warning23, R.string.tips23);
        Quiz quiz24 = new Quiz(24, EMAIL_QUIZ, R.string.message14, R.string.message24, false, R.string.warning24, R.string.tips24);
        Quiz quiz25 = new Quiz(25, EMAIL_QUIZ, R.string.message14, R.string.message25, false, R.string.warning25, R.string.tips25);
        Quiz quiz26 = new Quiz(26, EMAIL_QUIZ, R.string.message14, R.string.message26, false, R.string.warning26, R.string.tips26);
        Quiz quiz27 = new Quiz(27, EMAIL_QUIZ, R.string.message14, R.string.message27, false, R.string.warning27, R.string.tips27);
        Quiz quiz28 = new Quiz(28, EMAIL_QUIZ, R.string.message14, R.string.message28, false, R.string.warning28, R.string.tips28);
        Quiz quiz29 = new Quiz(29, EMAIL_QUIZ, R.string.message14, R.string.message29, false, R.string.warning29, R.string.tips29);
        Quiz quiz30 = new Quiz(30, EMAIL_QUIZ, R.string.message14, R.string.message30, false, R.string.warning30, R.string.tips30);

        // 添加所有 Quiz 对象到数据库
        this.quizRepository.insertQuizzes(quiz1);
        this.quizRepository.insertQuizzes(quiz2);
        this.quizRepository.insertQuizzes(quiz3);
        this.quizRepository.insertQuizzes(quiz4);
        this.quizRepository.insertQuizzes(quiz5);
        this.quizRepository.insertQuizzes(quiz6);
        this.quizRepository.insertQuizzes(quiz7);
        this.quizRepository.insertQuizzes(quiz8);
        this.quizRepository.insertQuizzes(quiz9);
        this.quizRepository.insertQuizzes(quiz10);
        this.quizRepository.insertQuizzes(quiz11);
        this.quizRepository.insertQuizzes(quiz12);
        this.quizRepository.insertQuizzes(quiz13);
        this.quizRepository.insertQuizzes(quiz14);
        this.quizRepository.insertQuizzes(quiz15);
        this.quizRepository.insertQuizzes(quiz16);
        this.quizRepository.insertQuizzes(quiz17);
        this.quizRepository.insertQuizzes(quiz18);
        this.quizRepository.insertQuizzes(quiz19);
        this.quizRepository.insertQuizzes(quiz20);
        this.quizRepository.insertQuizzes(quiz21);
        this.quizRepository.insertQuizzes(quiz22);
        this.quizRepository.insertQuizzes(quiz23);
        this.quizRepository.insertQuizzes(quiz24);
        this.quizRepository.insertQuizzes(quiz25);
        this.quizRepository.insertQuizzes(quiz26);
        this.quizRepository.insertQuizzes(quiz27);
        this.quizRepository.insertQuizzes(quiz28);
        this.quizRepository.insertQuizzes(quiz29);
        this.quizRepository.insertQuizzes(quiz30);
    }
}