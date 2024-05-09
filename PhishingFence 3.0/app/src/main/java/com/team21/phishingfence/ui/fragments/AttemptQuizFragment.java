package com.team21.phishingfence.ui.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.team21.phishingfence.R;


public class AttemptQuizFragment extends Fragment {

    private ImageButton imageButton;

    public AttemptQuizFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_attempt_quiz, container, false);

        this.imageButton = rootView.findViewById(R.id.imageButton);

        setButtonOnClickListener();

        return rootView;
    }

    private void setButtonOnClickListener() {
        setBackButtonListener();
    }

    private void setBackButtonListener() {
        this.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_attemptQuizFragment_to_scamKnowledgeCheckFragment);
            }
        });
    }
}