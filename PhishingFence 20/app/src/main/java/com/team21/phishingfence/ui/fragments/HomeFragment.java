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

public class HomeFragment extends Fragment {
    private Button buttonScamScenrio,buttonVerify;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_home, container, false);

        this.buttonScamScenrio = rootView.findViewById(R.id.buttonScamScenrio);
        this.buttonVerify = rootView.findViewById(R.id.buttonVerify);

        setButtonOnClickListener();

        return rootView;
    }

    private void setButtonOnClickListener() {
        this.buttonScamScenrio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_homeFragment_to_scamScenairoFragment);
            }
        });

        this.buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_homeFragment_to_verifyScamFragment);
            }
        });
    }
}