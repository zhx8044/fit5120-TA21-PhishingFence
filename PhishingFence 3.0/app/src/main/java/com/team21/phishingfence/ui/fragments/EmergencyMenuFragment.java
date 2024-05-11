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

public class EmergencyMenuFragment extends Fragment {
    private Button button,button2,button3;

    public EmergencyMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_emergency_menu, container, false);

        //初始化控件
        this.button = rootView.findViewById(R.id.button);
        this.button2 = rootView.findViewById(R.id.button2);
//        this.button3 = rootView.findViewById(R.id.button3);

        setButtonOnClickListener();

        return rootView;
    }

    private void setButtonOnClickListener() {
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_emergencyMenuFragment_to_reportScamFragment);
            }
        });

        this.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_emergencyMenuFragment_to_remedialActionsFragment);
            }
        });

//        this.button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                NavController controller = Navigation.findNavController(v);
//                controller.navigate(R.id.action_emergencyMenuFragment_to_legelSupportFragment);
//            }
//        });
    }
}