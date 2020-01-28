package com.example.labone.sample;

import android.content.Context;
import android.util.Log;

import com.example.labone.R;
import com.example.labone.model.Country;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


    public class JSONData {
        public static List<Country> countryList;

        static {
            countryList = new ArrayList<>();
        }

        public static List<Country> getJSON(Context context){
            String json = null;
            json = loadJSONFromRes(context); //load JSON from resource
            countryList = parseJSON(json); //parse JSON
            return countryList;
        }

        private static String loadJSONFromRes(Context context) {
            //opens the raw JSON file and assigns it to an InputStream instance
            InputStream inputStream = context.getResources().openRawResource(R.raw.countries);

            //stores the JSON as a String
            String jsonString = new Scanner(inputStream).useDelimiter("\\A").next();

            // https://stackoverflow.com/questions/6349759/using-json-file-in-android-app-resources
            // This uses the Java class Scanner, leading to less lines of code than some other methods of reading a simple text / json file. The delimiter pattern \A means 'the beginning of the input'. .next() reads the next token, which is the whole file in this case.

//        InputStream inputStream = null;
//        String jsonString = null;
//        try {
//            inputStream = context.getResources().openRawResource(R.raw.harrypotter);
//
//            int size = inputStream.available();
//            byte[] buffer = new byte[size];
//            inputStream.read(buffer);
//            jsonString = new String(buffer, "UTF-8");
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return null;
//        } finally {
//            try {
//                inputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

            return jsonString;
        }

        private static List<Country> parseJSON(String jsonString){
            if (jsonString != null) {
                try {
                    //create JSONObject
                    JSONObject jsonObject = new JSONObject(jsonString);

                    //create JSONArray with the value from the characters key
                    JSONArray countryArray = jsonObject.getJSONArray("EastAfricanCountries");

                    //loop through each object in the array
                    for (int i =0; i < countryArray.length(); i++) {
                        JSONObject countryObject = countryArray.getJSONObject(i);

                        //get values for name and info keys
                        String name = countryObject.getString("name");
                        String info = countryObject.getString("info");
                        String anthem=countryObject.getString("anthem");

                        //create new Character object
                        Country country = new Country(name, info,anthem);

                        //add character object to our ArrayList
                        countryList.add(country);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Log.d("Reached",countryList.get(0).getInfo());
            return countryList;
        }
    }

