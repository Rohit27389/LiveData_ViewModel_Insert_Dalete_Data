package com.example.activity_viewmodel_livedata;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.activity_viewmodel_livedata.database.NoteEntity;
import com.example.activity_viewmodel_livedata.model.NotesAdapter;
import com.example.activity_viewmodel_livedata.viewmodel.ListActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.notes_recyclerview)
    RecyclerView mRecyclerView;
    private ListActivityViewModel mListActivityViewModel;
    private List<NoteEntity> noteEntitiesList=new ArrayList<>();
    private NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewModel();

        ButterKnife.bind(this);
        initRecyclerView();
    }

    private void initViewModel() {
        Observer<List<NoteEntity>> notesObserver=new Observer<List<NoteEntity>>() {
            @Override
            public void onChanged(List<NoteEntity> noteEntities) {
                noteEntitiesList.clear();
                noteEntitiesList.addAll(noteEntities);
                if(notesAdapter==null){
                    notesAdapter=new NotesAdapter(MainActivity.this,noteEntitiesList);
                    mRecyclerView.setAdapter(notesAdapter);
                }else {
                    notesAdapter.notifyDataSetChanged();
                }
            }
        };
        mListActivityViewModel = ViewModelProviders.of(this)
                .get(ListActivityViewModel.class);
        mListActivityViewModel.viewNotifyEntity.observe(MainActivity.this,notesObserver);

    }

    private void initRecyclerView() {
        mRecyclerView.hasFixedSize();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch(id){
            case R.id.add_sample_data:{
                addSampleData();
                return true;
            }
            case R.id.delete_all_data:{
                deleteSampleData();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteSampleData() {
        mListActivityViewModel.deletSampleData();
    }

    private void addSampleData() {
        mListActivityViewModel.addSampleData();
    }


}