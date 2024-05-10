package com.team21.phishingfence.ui.fragments.awareness;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.team21.phishingfence.R;


public class TypesOfScamFragment extends Fragment {

    private ImageButton imageButton;

    private ConstraintLayout scam_channels,scam_scope,scam_communication;
//    private ConstraintLayout blocking_contacts,block_cards,strong_password,warn,report,create_IDCARE,scam_update;


    public TypesOfScamFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_types_of_scam, container, false);

        this.imageButton = rootView.findViewById(R.id.imageButton);
        this.scam_channels = rootView.findViewById(R.id.scam_channels);
        this.scam_scope = rootView.findViewById(R.id.scam_scope);
        this.scam_communication = rootView.findViewById(R.id.scam_communication);



        setButtonOnClickListener();

        return rootView;
    }


    private void setButtonOnClickListener() {
        setBackButtonListener();

        this.scam_channels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_typesOfScamFragment_to_scamChannelsFragment);
            }
        });

        this.scam_scope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_typesOfScamFragment_to_scamScopeFragment);
            }
        });

        this.scam_communication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_typesOfScamFragment_to_scamCommunicationFragment);
            }
        });
    }

    private void setBackButtonListener() {
        this.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_typesOfScamFragment_to_scamAwarenessFragment);
            }
        });
    }
}