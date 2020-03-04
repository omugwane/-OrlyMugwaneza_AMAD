package com.example.list;

import android.content.DialogInterface;
import android.os.Bundle;

import com.example.list.data.AppRepository;
import com.example.list.model.Student;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private AppRepository appRepository;
    private RecyclerView recyclerView;
    private FirestoreRecyclerAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the recycler view
        recyclerView = findViewById(R.id.recyclerView);

        //divider line between rows
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //set a layout manager on the recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setuprepo();

        setupadapter();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                LinearLayout layout = new LinearLayout(MainActivity.this); //try getApplicationContext()
                layout.setOrientation(LinearLayout.VERTICAL);


                final EditText firstNameEditText = new EditText(MainActivity.this);
                firstNameEditText.setHint("First Name");
                layout.addView(firstNameEditText);
                final EditText lastNameEditText = new EditText(MainActivity.this);
                lastNameEditText.setHint("Last Name");
                layout.addView(lastNameEditText);


                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Add Student");
                dialog.setView(layout);
                dialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String firstName = firstNameEditText.getText().toString();
                        String lastName = lastNameEditText.getText().toString();
                        if (firstName.trim().length()>0 && lastName.trim().length()>0) {

                            Student newStudent = new Student(firstName, lastName);

                            appRepository.insertStudent(newStudent);
                        }
                        Snackbar.make(view, "Student added", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                });

                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // cancel
                    }
                });

                dialog.show();
            }
        });
    }

    private void setuprepo() {
        appRepository = new AppRepository();
    }

    private void setupadapter() {
        FirestoreRecyclerOptions<Student> options = appRepository.getOptions();
        studentAdapter = new StudentAdapter(options, this, appRepository);
        recyclerView.setAdapter(studentAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        studentAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        studentAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_delete) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
