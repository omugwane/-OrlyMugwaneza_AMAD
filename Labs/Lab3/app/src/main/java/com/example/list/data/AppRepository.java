package com.example.list.data;

import com.example.list.model.Item;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class AppRepository {

    // Cloud Firestore instance
    private FirebaseFirestore db;

    //recipe collection
    private CollectionReference reciperef;

    public AppRepository(){
        db = FirebaseFirestore.getInstance();
        reciperef = db.collection("students");
    }

    //options to set up the adapter
    public FirestoreRecyclerOptions<Item> getOptions(){
        Query myquery = reciperef;
        FirestoreRecyclerOptions<Item> options = new FirestoreRecyclerOptions.Builder<Item>()
                .setQuery(myquery, Item.class)
                .build();
        return options;
    }

    public void insertRecipe(Item newItem){
        reciperef.add(newItem);
    }

    public void deleteRecipe(String id){
        reciperef.document(id).delete();
    }
}
