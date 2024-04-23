package com.team21.phishingfence.ui.fragments.emergency;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.team21.phishingfence.R;

public class ScamUpdatesFragment extends Fragment {
    private ImageButton imageButton;

    public ScamUpdatesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_scam_updates, container, false);

        this.imageButton = rootView.findViewById(R.id.imageButton);

        this.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_scamUpdatesFragment_to_remedialActionsFragment);
            }
        });

        return rootView;
    }
}