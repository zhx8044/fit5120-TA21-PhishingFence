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
import android.widget.ImageView;
import android.widget.TextView;

import com.team21.phishingfence.R;

public class ScoreFragment extends Fragment {

    public ScoreFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_score, container, false);

        TextView scoreTextView = rootView.findViewById(R.id.scoreTextView);
        TextView textView13 = rootView.findViewById(R.id.textView13);
        ImageButton imageButton = rootView.findViewById(R.id.imageButton);
        ImageView imageView = rootView.findViewById(R.id.imageView21);
        ImageView imageView2 = rootView.findViewById(R.id.imageView22);

        if (getArguments() != null) {
            int score = getArguments().getInt("score", 0); // 默认为 0
            // 根据获取的分数更新 UI 或进行其他操作
            scoreTextView.setText(getString(R.string.your_score) + score);
            imageView.setVisibility(View.VISIBLE);


            if(score == 7) {
                textView13.setText("Excellent.\nYou beat the scammers, but don't forget to keep your wits about you.");
            } else if (score >= 4 && score <= 6) {
                textView13.setText("Well done.\nYou’re pretty good at spotting a scam. Just don't let your guard down.");
            } else {
                imageView2.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.GONE);
                textView13.setText("Oh dear.\nThe scammers beat you this time. Take some time to read through the advice available.");
            }
        }

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_scoreFragment_to_scamKnowledgeCheckFragment);
            }
        });

        ImageButton imageButton2 = rootView.findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_scoreFragment_to_typesOfScamFragment);
            }
        });

        return rootView;
    }
}