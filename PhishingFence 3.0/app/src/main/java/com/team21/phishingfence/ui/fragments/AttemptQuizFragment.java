package com.team21.phishingfence.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.team21.phishingfence.R;
import com.team21.phishingfence.models.Quiz;
import com.team21.phishingfence.viewmodels.QuizViewModel;

public class AttemptQuizFragment extends Fragment {
    private QuizViewModel viewModel;
    private ImageButton imageButton;
    private ImageButton imageButton2;
    private TextView textViewQuestion,textViewEmailQuestion,rightHint,wrongHint,warning,tips;
    private RadioGroup chooseButton;
    private ImageView imgRight,imgWrong;
    private Boolean answer = true;
    private Boolean whetherChoosed = false;


    public AttemptQuizFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_attempt_quiz, container, false);

        this.imageButton = rootView.findViewById(R.id.imageButton);
        this.imageButton2 = rootView.findViewById(R.id.imageButton2);
        this.textViewQuestion = rootView.findViewById(R.id.textViewQuestion);
        this.textViewEmailQuestion = rootView.findViewById(R.id.textViewEmailQuestion);
        this.chooseButton = rootView.findViewById(R.id.choose);
        this.imgRight = rootView.findViewById(R.id.imgRight);
        this.imgWrong = rootView.findViewById(R.id.imgWrong);
        this.rightHint = rootView.findViewById(R.id.rightHint);
        this.wrongHint = rootView.findViewById(R.id.wrongHint);
        this.warning = rootView.findViewById(R.id.warning);
        this.tips = rootView.findViewById(R.id.tips);
        this.viewModel = new ViewModelProvider(requireActivity()).get(QuizViewModel.class);

        setButtonOnClickListener();
        setQuizNumObserver();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (this.viewModel.getQuizList() == null) {
            this.viewModel.generateQuizList();
        }
    }

    private void setQuizNumObserver() {
        this.viewModel.getQuizNum().observe(requireActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer >= 1 && integer <= 7) {
                    int id = AttemptQuizFragment.this.viewModel.getQuizList()[integer - 1];
                    Quiz quiz = AttemptQuizFragment.this.viewModel.getQuizById(id);
                    if(quiz.getType() == QuizViewModel.BASIC_QUIZ) {
                        AttemptQuizFragment.this.textViewQuestion.setVisibility(View.VISIBLE);
                        AttemptQuizFragment.this.textViewEmailQuestion.setVisibility(View.GONE);
                        AttemptQuizFragment.this.textViewQuestion.setText(requireActivity().getString(quiz.getQuestion()));
                    } else if (quiz.getType() == QuizViewModel.EMAIL_QUIZ) {
                        AttemptQuizFragment.this.textViewQuestion.setVisibility(View.GONE);
                        AttemptQuizFragment.this.textViewEmailQuestion.setVisibility(View.VISIBLE);
                        AttemptQuizFragment.this.textViewEmailQuestion.setText(requireActivity().getString(quiz.getPhoneMessage()));
                    } else {
                        AttemptQuizFragment.this.textViewQuestion.setVisibility(View.VISIBLE);
                        AttemptQuizFragment.this.textViewEmailQuestion.setVisibility(View.VISIBLE);
                        AttemptQuizFragment.this.textViewQuestion.setText(requireActivity().getString(quiz.getQuestion()));
                        AttemptQuizFragment.this.textViewEmailQuestion.setText(requireActivity().getString(quiz.getPhoneMessage()));
                    }
                    if(quiz.getAnswer()) {
                        AttemptQuizFragment.this.rightHint.setText(requireActivity().getString(R.string.right_hint2));
                        AttemptQuizFragment.this.wrongHint.setText(requireActivity().getString(R.string.wrongHint2));
                    } else {
                        AttemptQuizFragment.this.rightHint.setText(requireActivity().getString(R.string.right_hint));
                        AttemptQuizFragment.this.wrongHint.setText(requireActivity().getString(R.string.wrongHint));
                    }
                    AttemptQuizFragment.this.imgWrong.setVisibility(View.GONE);
                    AttemptQuizFragment.this.imgRight.setVisibility(View.GONE);
                    AttemptQuizFragment.this.rightHint.setVisibility(View.GONE);
                    AttemptQuizFragment.this.wrongHint.setVisibility(View.GONE);
                    AttemptQuizFragment.this.answer = quiz.getAnswer();
                    AttemptQuizFragment.this.warning.setText(requireActivity().getString(quiz.getWarning()));
                    AttemptQuizFragment.this.tips.setText(requireActivity().getString(quiz.getTips()));
                    AttemptQuizFragment.this.warning.setVisibility(View.GONE);
                    AttemptQuizFragment.this.tips.setVisibility(View.GONE);
                }
            }
        });
    }

    private void setButtonOnClickListener() {
        setBackButtonListener();
        setNextButtonListener();
        setRadioGroupListener();
    }

    private void setBackButtonListener() {
        this.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AttemptQuizFragment.this.viewModel.getQuizNum().setValue(-1);
                AttemptQuizFragment.this.viewModel.clearQuizList();

                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_attemptQuizFragment_to_scamKnowledgeCheckFragment);
            }
        });
    }

    private void setNextButtonListener() {
        this.imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AttemptQuizFragment.this.chooseButton.getCheckedRadioButtonId() != -1) {
                    AttemptQuizFragment.this.chooseButton.clearCheck();
                    if(AttemptQuizFragment.this.viewModel.getQuizNum().getValue() != null) {
                        if (AttemptQuizFragment.this.viewModel.getQuizNum().getValue() != 7) {
                            AttemptQuizFragment.this.viewModel.getQuizNum().setValue(
                                    AttemptQuizFragment.this.viewModel.getQuizNum().getValue() + 1);
                        } else if(AttemptQuizFragment.this.viewModel.getQuizNum().getValue() == -1) {
                            AttemptQuizFragment.this.viewModel.generateQuizList();
                        }
                        else {
                            AttemptQuizFragment.this.viewModel.getQuizNum().setValue(-1);
                            AttemptQuizFragment.this.viewModel.clearQuizList();
                            // 创建一个 Bundle 对象来存放分数
                            Bundle bundle = new Bundle();
                            bundle.putInt("score", AttemptQuizFragment.this.viewModel.getScore());

                            NavController controller = Navigation.findNavController(v);
                            controller.navigate(R.id.action_attemptQuizFragment_to_scoreFragment,bundle);
                        }
                        AttemptQuizFragment.this.whetherChoosed = false;
                    }
                }
            }
        });
    }

    private void setRadioGroupListener() {
        this.chooseButton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (!AttemptQuizFragment.this.whetherChoosed) {
                    AttemptQuizFragment.this.whetherChoosed = true;
                    boolean userChoose = checkedId == R.id.radioButtonScam;

                    if (userChoose == AttemptQuizFragment.this.answer) {
                        AttemptQuizFragment.this.imgRight.setVisibility(View.VISIBLE);
                        AttemptQuizFragment.this.rightHint.setVisibility(View.VISIBLE);
                        AttemptQuizFragment.this.imgWrong.setVisibility(View.GONE);
                        AttemptQuizFragment.this.wrongHint.setVisibility(View.GONE);
                        AttemptQuizFragment.this.viewModel.addScore();
                    } else {
                        AttemptQuizFragment.this.imgRight.setVisibility(View.GONE);
                        AttemptQuizFragment.this.rightHint.setVisibility(View.GONE);
                        AttemptQuizFragment.this.imgWrong.setVisibility(View.VISIBLE);
                        AttemptQuizFragment.this.wrongHint.setVisibility(View.VISIBLE);
                    }
                    AttemptQuizFragment.this.warning.setVisibility(View.VISIBLE);
                    AttemptQuizFragment.this.tips.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}