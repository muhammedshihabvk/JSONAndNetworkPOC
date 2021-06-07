package com.shabsudemy.jsonandnetworkpoc;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class JSONSample {

    public static String JSON_URL = "";

    public static void simpleJSONParser() {
        String JSON_STRING = "{\"employee\":{\"name\":\"shihab\",\"age\":12}}";
        String name;
        int age;

        try {
            JSONObject jsonObject = new JSONObject(JSON_STRING);
            JSONObject employee = jsonObject.getJSONObject("employee");

            name = employee.getString("name");
            age = employee.getInt("age");

            Log.i("shihab", name + "-->" + age);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void simpleJSONParserFromURLAsyncTask(String url) {
        JSON_URL = url;

        GetData getData = new GetData();
        getData.execute();


    }

    static class GetData extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            String current = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(JSON_URL);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(inputStream);

                int data = isr.read();
                while (data != -1) {
                    current += (char) data;
                    data = isr.read();
                }

                return current;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }

            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);

                HashMap<String, Object> dataMap = new HashMap<>();
                dataMap.put("success",jsonObject.getBoolean("success"));
                dataMap.put("message",jsonObject.getString("message"));
                dataMap.put("data",jsonObject.getString("data"));

                System.out.println(dataMap.get("data").toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }




}
