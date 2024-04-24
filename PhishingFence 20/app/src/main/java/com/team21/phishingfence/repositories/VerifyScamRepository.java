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
//    private final static String apiEndpoint = "https://nbhq8mbflg.execute-api.ap-southeast-2.amazonaws.com/default/testnow";
    private final static String apiEndpoint = "https://bxa6v3wtpe.execute-api.ap-southeast-2.amazonaws.com/default/SpamSMSdetection";

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

//        @Override
//        protected void onPostExecute(String result) {
//            try {
//                JSONObject jsonResponse = new JSONObject(result);
//                String formattedResult = jsonResponse.getString("formattedResult");
//
//                // Extract probabilities from formattedResult assuming they are in the format:
//                // "Probability of being ham: [hamProbability]\nProbability of being spam: [spamProbability]"
//                String[] lines = formattedResult.split("\\n");
//                double hamProbability = Double.parseDouble(lines[2].split(": ")[1]);
//                double spamProbability = Double.parseDouble(lines[3].split(": ")[1]);
//
//                // Determine the predicted class based on probabilities
//                String predictedClass = spamProbability > hamProbability ? "spam" : "ham";
//
//                // Replace the predicted class in formattedResult
//                formattedResult = formattedResult.replaceAll("Predicted class: (spam|ham)", "Predicted class: " + predictedClass);
//
//                if (resultView != null) {
//                    resultView.setText(formattedResult);
//                }
//            } catch (JSONException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
//                e.printStackTrace();
//                if (resultView != null) {
//                    resultView.setText("Error parsing the response or formattedResult.");
//                }
//            }
//        }

        // 处理由Gateway API 返回的json格式信息
        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonResponse = new JSONObject(result);
                // 从Lambda函数的响应中直接获取预测的类别和概率
                /* result 格式如下
                {
                    "statusCode": 200,
                        "body": "{\"Predicted class\": \"ham\", \"Probability of being ham\": 0.9875905427682923, \"Probability of being spam\": 0.01240945723170767}"
                }
                 */

                String predictedClass = jsonResponse.getString("Predicted class");
                double hamProbability = jsonResponse.getDouble("Probability of being ham");
                double spamProbability = jsonResponse.getDouble("Probability of being spam");

                // 转换概率为百分比形式，保留小数点后四位
                String hamProbabilityString = String.format("%.4f%%", hamProbability * 100);
                String spamProbabilityString = String.format("%.4f%%", spamProbability * 100);

                // 构造格式化后的结果字符串
                String formattedResult = "Predicted class: " + predictedClass + "\n";
                formattedResult += "Probability of being ham: " + hamProbabilityString + "\n";
                formattedResult += "Probability of being spam: " + spamProbabilityString;


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
