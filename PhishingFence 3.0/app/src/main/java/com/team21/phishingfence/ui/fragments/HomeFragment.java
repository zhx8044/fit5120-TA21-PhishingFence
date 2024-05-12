package com.team21.phishingfence.ui.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.team21.phishingfence.R;

import com.team21.phishingfence.ui.HelpDialog;  // 确保导入了HelpDialog


public class HomeFragment extends Fragment {
    private Button buttonScamScenrio,buttonVerify;

    private View helpOverlay;// 帮助

    private SharedPreferences sharedPreferences;


    public HomeFragment() {
        // Required empty public constructor
    }

////加入帮助
//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
////        // 显示帮助对话框
////        HelpDialog helpDialog = new HelpDialog(getContext(), "欢迎使用! 这里是一些使用提示。");
////        helpDialog.show();
//    }

    //原来的版本

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View rootView =  inflater.inflate(R.layout.fragment_home, container, false);
//
//        this.buttonScamScenrio = rootView.findViewById(R.id.buttonScamScenrio);
//        this.buttonVerify = rootView.findViewById(R.id.buttonVerify);
//
//        setButtonOnClickListener();
//
//        return rootView;
//    }

    //帮助版本

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
//
//        this.buttonScamScenrio = rootView.findViewById(R.id.buttonScamScenrio);
//        this.buttonVerify = rootView.findViewById(R.id.buttonVerify);
//        this.helpOverlay = rootView.findViewById(R.id.helpOverlay); // 获取帮助浮层的视图
//
//        rootView.findViewById(R.id.closeHelpButton).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                helpOverlay.setVisibility(View.GONE); // 点击关闭按钮时隐藏帮助浮层
//            }
//        });
//
//        setButtonOnClickListener();
//
//        return rootView;
//    }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_home, container, false);

            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            boolean isHelpClosed = sharedPreferences.getBoolean("help_closed", false);

            this.buttonScamScenrio = rootView.findViewById(R.id.buttonScamScenrio);
            this.buttonVerify = rootView.findViewById(R.id.buttonVerify);
            this.helpOverlay = rootView.findViewById(R.id.helpOverlay);

            if (isHelpClosed) {
                helpOverlay.setVisibility(View.GONE);
            }

            rootView.findViewById(R.id.closeHelpButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    helpOverlay.setVisibility(View.GONE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("help_closed", true);
                    editor.apply();
                }
            });

            setButtonOnClickListener();

            return rootView;
        }


    // 帮助--

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