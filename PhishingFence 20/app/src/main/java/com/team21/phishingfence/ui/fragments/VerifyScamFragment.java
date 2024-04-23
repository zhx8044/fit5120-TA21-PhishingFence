package com.team21.phishingfence.ui.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.phishingfence.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VerifyScamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VerifyScamFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText editTextMessage;
    private TextView textViewResults;

    public VerifyScamFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VerifyScamFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VerifyScamFragment newInstance(String param1, String param2) {
        VerifyScamFragment fragment = new VerifyScamFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_verify_scam, container, false);
//    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_verify_scam, container, false);

        editTextMessage = view.findViewById(R.id.edit_text_message);
        textViewResults = view.findViewById(R.id.text_view_results);
        view.findViewById(R.id.button_verify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();//隐藏键盘
                String apiEndpoint = "https://nbhq8mbflg.execute-api.ap-southeast-2.amazonaws.com/default/testnow";
                new InvokeAPIAsyncTask(textViewResults).execute(apiEndpoint, editTextMessage.getText().toString());
            }
        });

        return view;
    }

    private static class InvokeAPIAsyncTask extends AsyncTask<String, Void, String> {
        private TextView resultView;

        InvokeAPIAsyncTask(TextView resultView) {
            this.resultView = resultView;
        }

    @Override
    protected String doInBackground(String... strings) {
        String endpoint = strings[0];
        String inputMessage = strings[1];
        try {
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            // 创建一个包含消息的JSON对象
            JSONObject jsonMessage = new JSONObject();
            jsonMessage.put("message", inputMessage);

            // 发送JSON字符串
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonMessage.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                return response.toString();
            } else {
                return "Error: " + responseCode;
            }
        } catch (Exception e) {
            return "Exception: " + e.getMessage();
        }
    }


        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonResponse = new JSONObject(result);
                String formattedResult = jsonResponse.getString("formattedResult");
                if (resultView != null) {
                    resultView.setText(formattedResult);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                if (resultView != null) {
                    resultView.setText("Error parsing the response.");
                }
            }
        }

    }

    private void hideKeyboard() { //再次糊弄，隐藏键盘
        if (getActivity() == null || getActivity().getCurrentFocus() == null) return;

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }
}