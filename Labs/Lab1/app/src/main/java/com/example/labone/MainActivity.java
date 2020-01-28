package com.example.labone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.labone.model.Country;
import com.example.labone.sample.JSONData;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ListAdapter.ItemClickListener  {
    List<Country> countryList= JSONData.countryList;

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Countries");
        imageView=findViewById(R.id.imageView);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.eastafricanflag));

        //get data
        if (countryList.isEmpty()) {

            countryList = JSONData.getJSON(this);
            //Log.d("CountryIsEmpty",countryList.get(1).getName());
        }

        //get the recycler view
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        //divider line between rows
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //define an adapter
        ListAdapter adapter = new ListAdapter(countryList, this, this);

        //assign the adapter to the recycler view
        recyclerView.setAdapter(adapter);

        //set a layout manager on the recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemClick(int position) {
        //opens web page in detail activity
//        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
//        intent.putExtra("id", position);

        //opens web page in browser
        Intent intent = new Intent((Intent.ACTION_VIEW));
        Country country = JSONData.countryList.get(position);
        String webString = country.getInfo();
        intent.setData(Uri.parse(webString));

        //starts new activity
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.info:
                return true;
            case R.id.anthem:
                Intent i=new Intent(MainActivity.this,NationalAnthem.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
