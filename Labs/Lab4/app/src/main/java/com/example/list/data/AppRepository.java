package com.example.list.data;

import com.example.list.model.Student;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class AppRepository {

    // Cloud Firestore instance
    private FirebaseFirestore db;

    //recipe collection
    private CollectionReference studentref;

    public AppRepository(){
        db = FirebaseFirestore.getInstance();
        studentref = db.collection("students");
    }

    //options to set up the adapter
    public FirestoreRecyclerOptions<Student> getOptions(){
        Query myquery = studentref;
        FirestoreRecyclerOptions<Student> options = new FirestoreRecyclerOptions.Builder<Student>()
                .setQuery(myquery, Student.class)
                .build();
        return options;
    }

    public void insertStudent(Student newRecipe){
        studentref.add(newRecipe);
    }

    public void deleteStudent(String id){
        studentref.document(id).delete();
    }
}
