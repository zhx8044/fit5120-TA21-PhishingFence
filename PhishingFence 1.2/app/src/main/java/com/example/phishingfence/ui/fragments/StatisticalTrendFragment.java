package com.example.phishingfence.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.phishingfence.R;

import java.util.ArrayList;
import java.util.List;

public class StatisticalTrendFragment extends Fragment
{
    private ImageView btnBack;
    private OnStatisticalTrendFragmentInteractionListener mOnStatisticalTrendFragmentInteractionListener;

    public StatisticalTrendFragment()
    {
        // Required empty public constructor
    }

    public interface OnStatisticalTrendFragmentInteractionListener
    {
        void onBackClick();
    }

    public void setOnStatisticalTrendFragmentInteractionListener(OnStatisticalTrendFragmentInteractionListener onStatisticalTrendFragmentInteractionListener)
    {
        this.mOnStatisticalTrendFragmentInteractionListener = onStatisticalTrendFragmentInteractionListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_statistical_trend, container, false);

        //初始化控件
        this.btnBack = rootview.findViewById(R.id.img_back);

        setupClickListeners();

        return rootview;
    }

    private void setupClickListeners()
    {
        setupBackButtonListener();
    }

    private void setupBackButtonListener()
    {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnStatisticalTrendFragmentInteractionListener!=null)
                {
                    mOnStatisticalTrendFragmentInteractionListener.onBackClick();
                }
            }
        });
    }
}