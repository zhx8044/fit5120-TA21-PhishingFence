package com.team21.phishingfence.repositories;

import android.os.AsyncTask;
import android.widget.TextView;

import com.team21.phishingfence.ui.fragments.VerifyScamFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class VerifyScamRepository {
    private final static String apiEndpoint = "https://nbhq8mbflg.execute-api.ap-southeast-2.amazonaws.com/default/testnow";

    /**
     * 使用该方法进行验证逻辑
     */
    public void verify(String message,TextView textView) {
        new InvokeAPIAsyncTask(textView).execute(apiEndpoint, message);
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
}
