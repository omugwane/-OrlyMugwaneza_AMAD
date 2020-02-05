package com.example.labtwo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.labtwo.model.Meme;
import com.example.labtwo.loader.JSONData;

import java.util.List;

public class MainActivity extends AppCompatActivity{
    List<Meme> memeList =JSONData.memeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Memes");

        //get data
        if (memeList.isEmpty()) {
            //topMovieList = JSONData.getJSON();
            JSONData.getJSON(this);
        }

        //get the recycler view
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        //define an adapter
        MyListAdapter adapter = new MyListAdapter(memeList, this);

        //assign the adapter to the recycler view
        recyclerView.setAdapter(adapter);

        //set a layout manager on the recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
