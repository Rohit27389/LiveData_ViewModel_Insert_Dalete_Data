package com.example.activity_viewmodel_livedata.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.activity_viewmodel_livedata.R;
import com.example.activity_viewmodel_livedata.database.NoteEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    private Context mContext;
    private List<NoteEntity> mDataList;

    public NotesAdapter(Context mContext, List<NoteEntity> mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(mContext).inflate(R.layout.note_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        NoteEntity noteEntity=mDataList.get(position);
        holder.textView.setText(noteEntity.getText());

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.note_text)
        TextView textView;

        @BindView(R.id.fab_edit_note)
        FloatingActionButton fab;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}