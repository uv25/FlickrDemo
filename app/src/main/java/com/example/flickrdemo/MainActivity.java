package com.example.flickrdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    String tag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView userList = (RecyclerView) findViewById(R.id.userList);
        userList.setLayoutManager(new GridLayoutManager(this, 2));

        // Instantiate the RequestQueue.
        final RequestQueue queue = Volley.newRequestQueue(this);


        searchView = (SearchView) findViewById(R.id.search_bar);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String url ="https://www.flickr.com/services/rest/?method=flickr.photos.search&api_key=a484956da8703c4397e2e174fa0206b9&text="+query+"&per_page=500&format=json&nojsoncallback=1";
                StringRequest stringRequest = new StringRequest(url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.

                                int startIndex = response.indexOf('[');
                                int endIndex = response.lastIndexOf(']');
                                //Log.d("Code", response);
                                response = response.substring(startIndex, endIndex+1);
                                //Log.d("trimmed response", response);
                                GsonBuilder gsonBuilder = new GsonBuilder();
                                Gson gson = gsonBuilder.create();
                                Pic[] pics = gson.fromJson(response, Pic[].class);
                                userList.setAdapter(new FlickrAdapter(MainActivity.this, pics));
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //Log.d("failed to load", "failed");
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(stringRequest);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
