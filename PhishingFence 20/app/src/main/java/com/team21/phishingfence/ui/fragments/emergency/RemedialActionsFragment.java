package com.team21.phishingfence.ui.fragments.emergency;

import android.os.Bundle;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.team21.phishingfence.R;


public class RemedialActionsFragment extends Fragment {
    private ImageButton imageButton;
    private LinearLayoutCompat blocking_contacts,block_cards,strong_password,warn,report,create_IDCARE,scam_update;

    public RemedialActionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_remedial_actions, container, false);

        this.imageButton = rootView.findViewById(R.id.imageButton);
        this.blocking_contacts = rootView.findViewById(R.id.blocking_contacts);
        this.block_cards = rootView.findViewById(R.id.block_cards);
        this.strong_password = rootView.findViewById(R.id.strong_password);
        this.warn = rootView.findViewById(R.id.warn);
        this.report = rootView.findViewById(R.id.report);
        this.create_IDCARE = rootView.findViewById(R.id.create_IDCARE);
        this.scam_update = rootView.findViewById(R.id.scam_update);

        setButtonOnClickListener();

        return rootView;
    }

    private void setButtonOnClickListener() {
        setBackButtonListener();

        this.blocking_contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_remedialActionsFragment_to_blockingContactsFragment);
            }
        });

        this.block_cards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_remedialActionsFragment_to_blockCardsFragment);
            }
        });

        this.strong_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_remedialActionsFragment_to_strongPasswordFragment);
            }
        });

        this.warn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_remedialActionsFragment_to_warnFragment);
            }
        });

        this.report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_remedialActionsFragment_to_reportScamFragment);
            }
        });

        this.create_IDCARE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_remedialActionsFragment_to_contactIDCAREFragment);
            }
        });

        this.scam_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_remedialActionsFragment_to_scamUpdatesFragment);
            }
        });
    }

    private void setBackButtonListener() {
        this.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_remedialActionsFragment_to_emergencyMenuFragment);
            }
        });
    }
}