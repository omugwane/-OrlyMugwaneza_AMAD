package com.example.list;

import android.content.Context;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.list.model.Item;
import com.example.list.model.ItemViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
    private List<Item> mItemList;
    private ItemViewModel mItemViewModel;
    private Context mContext;

    public MyListAdapter(ItemViewModel mItemViewModel, Context mContext) {
        this.mItemViewModel = mItemViewModel;
        mItemList = mItemViewModel.getItemList().getValue();
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyListAdapter.ViewHolder holder, int position) {
        final Item item = mItemList.get(position);
        holder.mTextView.setText(item.getFirstName());
        holder.mPriority.setText(item.getLastName());
        //context menu
        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener(){
            @Override
            public void onCreateContextMenu(ContextMenu menu, final View v, ContextMenu.ContextMenuInfo menuInfo) {
                //set the menu title
                menu.setHeaderTitle("Remove " + item.getFirstName() +" "+item.getLastName());
                //add the choices to the menu
                menu.add(1, 1, 1, "Yes").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int position = holder.getAdapterPosition();
                        mItemList.remove(position);
                        mItemViewModel.getItemList().setValue(mItemList);
                        Snackbar.make(v, "Student removed", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        return false;
                    }
                });
                menu.add(2, 2, 2, "No");
            }
        });
    }

    @Override
    public int getItemCount() {
        if ( mItemList != null) {
            return mItemList.size();
        }
        else return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        TextView mPriority;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.textView);
            mPriority=itemView.findViewById(R.id.priority);
        }
    }
}