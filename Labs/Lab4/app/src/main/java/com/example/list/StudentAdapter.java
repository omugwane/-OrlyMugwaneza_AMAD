package com.example.list;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.list.data.AppRepository;

import com.example.list.model.Student;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.snackbar.Snackbar;

public class StudentAdapter extends FirestoreRecyclerAdapter<Student, StudentViewHolder>  {
    private Context mContext;
    private AppRepository mappRepository;

    public StudentAdapter(FirestoreRecyclerOptions<Student> students, Context context, AppRepository appRepository){
        super(students);
        this.mContext = context;
        this.mappRepository = appRepository;
    }

    @Override
    protected void onBindViewHolder(@NonNull final StudentViewHolder studentViewHolder, int i, @NonNull final Student student) {
        studentViewHolder.setFirstName(student.getFirstName());
        studentViewHolder.setLastName(student.getLastName());


        //context menu
        studentViewHolder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, final View v, ContextMenu.ContextMenuInfo menuInfo) {

                menu.setHeaderTitle("Delete " + student.getFirstName() +" "+student.getLastName());

                menu.add(1, 1, 1, "Yes").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        int position = studentViewHolder.getAdapterPosition();

                        String id = getSnapshots().getSnapshot(position).getId();

                        mappRepository.deleteStudent(id);

                        Snackbar.make(v, "Student removed", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                        return false;
                    }
                });
                menu.add(2, 2, 2, "No");
            }
        });
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        StudentViewHolder studentViewHolder = new StudentViewHolder(itemView);
        return studentViewHolder;
    }
}
