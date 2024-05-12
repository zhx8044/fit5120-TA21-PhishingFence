package com.team21.phishingfence.ui.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.team21.phishingfence.R;

public class ScoreFragment extends Fragment {
    private TextView scoreTextView;
    private ImageButton imageButton;

    public ScoreFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_score, container, false);

        this.scoreTextView = rootView.findViewById(R.id.scoreTextView);
        this.imageButton = rootView.findViewById(R.id.imageButton);
        if (getArguments() != null) {
            int score = getArguments().getInt("score", 0); // 默认为 0
            // 根据获取的分数更新 UI 或进行其他操作
            this.scoreTextView.setText(getString(R.string.your_score) + score);
        }

        this.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_scoreFragment_to_scamKnowledgeCheckFragment);
            }
        });

        return rootView;
    }
}