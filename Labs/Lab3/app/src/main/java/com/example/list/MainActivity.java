package com.example.list;

import android.content.DialogInterface;
import android.os.Bundle;

import com.example.list.model.Item;
import com.example.list.model.ItemViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Item> mItemList;
    private ItemViewModel itemViewModel;
    private MyListAdapter listAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //create alert dialog
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                LinearLayout layout = new LinearLayout(MainActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);

                //create edit text
                final EditText edittext = new EditText(getApplicationContext());
                edittext.setHint("First Name");
                layout.addView(edittext);
                final EditText priority = new EditText(getApplicationContext());
                priority.setHint("Last Name");
                layout.addView(priority);
                //add edit text to dialog


                dialog.setView(layout);
                //set dialog title
                dialog.setTitle("Add Student");
                //sets OK action
                dialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //get item entered
                        String newItem = edittext.getText().toString();
                        String priorty= priority.getText().toString();
                        if (!newItem.isEmpty()) {
                            // add item
                            mItemList.add(new Item(newItem,priorty));
                            itemViewModel.getItemList().setValue(mItemList);
                        }
                        Snackbar.make(view, "Student added", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                });
                //sets cancel action
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // cancel
                    }
                });
                //present alert dialog
                dialog.show();
            }
        });

        //create a viewmodel
        itemViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(this.getApplication())).get(ItemViewModel.class);

        if (itemViewModel.getItemList().getValue() == null){
            mItemList = new ArrayList<>();
            itemViewModel.loadData();
        }

        //get data
        mItemList = itemViewModel.getItemList().getValue();
        //get the recycler view
        recyclerView = findViewById(R.id.recyclerView);

        //divider line between rows
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //set a layout manager on the recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //create the observer
        final Observer<List<Item>> itemObserver = new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                if (listAdapter == null) {
                    //define the adapter
                    listAdapter = new MyListAdapter(itemViewModel, MainActivity.this);
                    //assign the adapter to the recycle view
                    recyclerView.setAdapter(listAdapter);
                } else {
                    listAdapter.notifyDataSetChanged();
                    itemViewModel.saveData();
                }
            }
        };

        //set the observer
        itemViewModel.getItemList().observe(this, itemObserver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
