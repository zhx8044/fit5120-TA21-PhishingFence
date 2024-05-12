package com.team21.phishingfence.ui.fragments.awareness;

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


public class ScamCommunicationFragment extends Fragment {

    private ImageButton imageButton;
    private TextView textView1;
    public ScamCommunicationFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_scam_communication, container, false);

        this.imageButton = rootView.findViewById(R.id.imageButton);
        this.textView1 = rootView.findViewById(R.id.textView1);
        this.textView1.setTextIsSelectable(true);

        this.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_scamCommunicationFragment_to_typesOfScamFragment);
            }
        });

        return rootView;
    }
}