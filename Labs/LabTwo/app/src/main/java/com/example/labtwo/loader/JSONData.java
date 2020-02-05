package com.example.labtwo.loader;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.labtwo.model.Meme;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONData {
    public static List<Meme> memeList;
    private static String API_URL="https://alpha-meme-maker.herokuapp.com";

    static {
        memeList = new ArrayList<>();
    }

    public static void getJSON(Context context){
        String url = API_URL;

        // create Volley request queue
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        parseJSON(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR", error.getMessage(), error);
            }
        });
        queue.add(stringRequest);
    }

    private static List<Meme> parseJSON(String response){
        // Base url for the posters
        //final String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w185";

        if (response != null) {
            try {
                //create JSONObject
                JSONObject jsonObject = new JSONObject(response);


                //create JSONArray with the value from the characters key
                JSONArray resultsArray = jsonObject.getJSONArray("data");
                Log.d("dataLength",""+resultsArray.length());
                //loop through each object in the array
                for (int i =0; i < resultsArray.length(); i++) {
                    JSONObject charObject = resultsArray.getJSONObject(i);

                    //get values
                    int id = charObject.getInt("ID");

                    String bottomText = charObject.getString("bottomText");
                    String topText = charObject.getString("topText");
                    String rank =String.valueOf( charObject.getInt("rank"));

                    //save the fully qualified URL for the poster image
                    String imageURL = charObject.getString("image");
                    String name = String.valueOf(charObject.getString("name"));
                    System.out.println(name);


                    //create new Movie object
                    Meme meme = new Meme( id, bottomText,  name, imageURL, topText,rank);

                    //add movie object to our ArrayList
                    memeList.add(meme);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.d("memeListSize",""+memeList.size());
        return memeList;
    }
}
