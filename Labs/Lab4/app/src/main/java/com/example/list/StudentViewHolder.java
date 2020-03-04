package com.example.list;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentViewHolder extends RecyclerView.ViewHolder{
    private TextView firstNameTextView;
    private TextView lastNameTextView;

    public StudentViewHolder(@NonNull View itemView) {
        super(itemView);
        firstNameTextView = itemView.findViewById(R.id.textFirstName);
        lastNameTextView=itemView.findViewById(R.id.textLastName);
    }

    public void setFirstName(String name){
        firstNameTextView.setText(name);
    }
    public void setLastName(String name){
        lastNameTextView.setText(name);
    }
}

