package com.example.phishingfence.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.phishingfence.R;

public class HomeFragment extends Fragment
{
    private Button btnScamScenario;
    private Button btnVerifyScam;
    private OnHomeFragmentInteractionListener mOnHomeFragmentInteractionListener;

    public HomeFragment()
    {
        // Required empty public constructor
    }

    public interface OnHomeFragmentInteractionListener
    {
        void onScamScenarioClick();
        void onVerifyScamClick();
    }

    public void setOnHomeFragmentInteractionListener(OnHomeFragmentInteractionListener onHomeFragmentInteractionListener)
    {
        this.mOnHomeFragmentInteractionListener = onHomeFragmentInteractionListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // 这里可以保留对Fragment的全局初始化（如果有的话）
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_home, container, false);

        // 初始化控件
        this.btnScamScenario = rootview.findViewById(R.id.btn_scam_scenario);
//        this.btnVerifyScam = rootview.findViewById(R.id.btn_verify_scam);

        setupClickListeners();

        return rootview;
    }

    private void setupClickListeners()
    {
        setupScamScenarioButtonListener();
//        setupVerifyScamButtonListener();
    }

    private void setupScamScenarioButtonListener()
    {
        btnScamScenario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnHomeFragmentInteractionListener!=null)
                {
                    mOnHomeFragmentInteractionListener.onScamScenarioClick();
                }
            }
        });
    }

//    private void setupVerifyScamButtonListener()
//    {
//        btnVerifyScam.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(mOnHomeFragmentInteractionListener!=null)
//                {
//                    mOnHomeFragmentInteractionListener.onVerifyScamClick();
//                }
//            }
//        });
//    }
}