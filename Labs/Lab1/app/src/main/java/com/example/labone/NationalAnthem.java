package com.example.labone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.labone.model.Country;
import com.example.labone.sample.JSONData;

public class NationalAnthem extends MainActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.imageView.setImageDrawable(getResources().getDrawable(R.drawable.nationalanthemicon));
    }
    @Override
    public void onItemClick(int position) {
       // opens web page in detail activity
        Intent intent = new Intent(NationalAnthem.this, DetailActivity.class);
        intent.putExtra("id", position);

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
                Intent i=new Intent(NationalAnthem.this,MainActivity.class);
                startActivity(i);
                return true;
            case R.id.anthem:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
