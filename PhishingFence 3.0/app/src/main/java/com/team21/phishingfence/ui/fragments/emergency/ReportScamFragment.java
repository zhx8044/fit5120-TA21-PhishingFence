package com.team21.phishingfence.ui.fragments.emergency;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.team21.phishingfence.R;

import java.util.Objects;

public class ReportScamFragment extends Fragment {
    private ImageButton imageButton;
    private TextView textView1;

    public ReportScamFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_report_scam, container, false);

        this.imageButton = rootView.findViewById(R.id.imageButton);
        this.textView1 = rootView.findViewById(R.id.textView1);
        this.textView1.setTextIsSelectable(true);

        String s = getArguments().getString("aaa");

        if(Objects.equals(s, "AAA")) {
            this.imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController controller = Navigation.findNavController(v);
                    controller.navigate(R.id.action_reportScamFragment_to_remedialActionsFragment);
                }
            });
        } else {
            this.imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController controller = Navigation.findNavController(v);
                    controller.navigate(R.id.action_reportScamFragment_to_emergencyMenuFragment);
                }
            });
        }

        return rootView;
    }
}