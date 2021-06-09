package com.shabsudemy.jsonandnetworkpoc.JSONToRV;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shabsudemy.jsonandnetworkpoc.R;
import com.shabsudemy.jsonandnetworkpoc.models.Album;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

interface AsyncResponse {
    void processFinish(String output);
}

public class JSONToRVActivity extends AppCompatActivity implements AsyncResponse {

    RecyclerView recyclerView;
    List<Album> dataModelList;
    JSONTORVAdapter jsontorvAdapter;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsonto_rvactivity);

        recyclerView = findViewById(R.id.jsonToRvRecyclerView);
        progressBar = findViewById(R.id.progressCircle);
        Log.d("TAG", "1");
        dataModelList = new ArrayList<>();

        GetData getData = new GetData("https://jsonplaceholder.typicode.com/photos", this);
        getData.execute();
        Log.d("TAG", "2");

    }

    @Override
    public void processFinish(String output) {
        dataModelList = generateAlbumFromJson(output);
        for (Album a : dataModelList) {
            Log.d("TAG", a.getTitle());
        }
        jsontorvAdapter = new JSONTORVAdapter(this, dataModelList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(jsontorvAdapter);
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        Log.d("TAG", "3");
    }

    public List<Album> generateAlbumFromJson(String json) {
        List<Album> generatedList = new ArrayList<Album>();
        try {
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                Album album = new Album();
                album.setAlbumId(jsonArray.getJSONObject(i).getInt("albumId"));
                album.setId(jsonArray.getJSONObject(i).getInt("id"));
                album.setTitle(jsonArray.getJSONObject(i).getString("title"));
                album.setThumbnailUrl(jsonArray.getJSONObject(i).getString("thumbnailUrl"));
                album.setUrl(jsonArray.getJSONObject(i).getString("url"));
                generatedList.add(album);
            }
            return generatedList;

        } catch (JSONException e) {
            e.printStackTrace();
            return generatedList;
        }
    }

    class GetData extends AsyncTask<String, String, String> {

        public AsyncResponse delegate = null;
        String apiURL = "";


        GetData(String url, AsyncResponse delegate) {
            this.apiURL = url;
            this.delegate = delegate;
        }

        @Override
        protected String doInBackground(String... strings) {
            String current = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(apiURL);
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
            delegate.processFinish(s);
        }
    }
}

