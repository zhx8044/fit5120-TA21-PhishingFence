package com.team21.phishingfence.ui.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.team21.phishingfence.R;
import com.team21.phishingfence.viewmodels.VerifyScamViewmodel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class VerifyScamFragment extends Fragment {
    private EditText editTextMessage;
    private TextView textViewResults;
    private VerifyScamViewmodel viewmodel;

    public VerifyScamFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_verify, container, false);

        this.editTextMessage = view.findViewById(R.id.edit_text_message);
        this.textViewResults = view.findViewById(R.id.text_view_results);
        this.textViewResults.setTextIsSelectable(true);
        this.viewmodel = new ViewModelProvider(requireActivity()).get(VerifyScamViewmodel.class);//获取ViewModel

        if(this.viewmodel.getResult() != null) {
            this.textViewResults.setText(this.viewmodel.getResult());
        }

        view.findViewById(R.id.button_verify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();//隐藏键盘
                /*
                String apiEndpoint = "https://nbhq8mbflg.execute-api.ap-southeast-2.amazonaws.com/default/testnow";
                new InvokeAPIAsyncTask(textViewResults).execute(apiEndpoint, editTextMessage.getText().toString());
                 */

                VerifyScamFragment.this.viewmodel.verify(VerifyScamFragment.this.textViewResults);
                Toast.makeText(requireActivity(), "verifying", Toast.LENGTH_LONG).show();
            }
        });

        // 在目标Fragment的onCreate或onCreateView中
        Bundle bundle = getArguments();
        if (bundle != null) {
            String value = bundle.getString("message to verify");
            this.editTextMessage.setText(value);
        }

        setEditTextMessageListener();

        return view;
    }


    private void hideKeyboard() { //再次糊弄，隐藏键盘
        if (getActivity() == null || getActivity().getCurrentFocus() == null) return;

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }

    /**
     * 设置editText观察者，在用户输入或删除时将用户输入传给ViewModel
     */
    private void setEditTextMessageListener() {
        this.editTextMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                VerifyScamFragment.this.viewmodel.setMessage(VerifyScamFragment.this.editTextMessage.getText().toString());
                if (TextUtils.isEmpty(VerifyScamFragment.this.editTextMessage.getText())) {
                    //如果用户删除所有的输入则改变显示结果
                    VerifyScamFragment.this.viewmodel.setResult(null);
                }
            }
        });
    }
}