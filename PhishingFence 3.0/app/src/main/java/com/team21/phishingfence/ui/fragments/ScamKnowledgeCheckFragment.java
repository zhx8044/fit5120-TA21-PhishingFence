package com.team21.phishingfence.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.team21.phishingfence.R;

public class ScamKnowledgeCheckFragment extends Fragment {
    private Button button;
    public ScamKnowledgeCheckFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_scam_knowledge_check, container, false);

        //初始化控件
        this.button = rootView.findViewById(R.id.button);

        setButtonOnClickListener();

        return rootView;
    }

    private void setButtonOnClickListener() {
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_scamKnowledgeCheckFragment_to_attemptQuizFragment);
            }
        });
    }

}