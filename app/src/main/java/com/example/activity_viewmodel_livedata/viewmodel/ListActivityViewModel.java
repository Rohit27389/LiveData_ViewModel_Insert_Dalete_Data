package com.example.activity_viewmodel_livedata.viewmodel;

import android.app.Application;

import com.example.activity_viewmodel_livedata.database.AppRepository;
import com.example.activity_viewmodel_livedata.database.NoteEntity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ListActivityViewModel extends AndroidViewModel {

    public LiveData<List<NoteEntity>> viewNotifyEntity;
    public AppRepository mAppRepository;

    public ListActivityViewModel(@NonNull Application application) {
        super(application);
        mAppRepository=AppRepository.getInstance(application.getApplicationContext());
        viewNotifyEntity=mAppRepository.repoNotes;
    }
    public void addSampleData() {
        mAppRepository.addSampleData();
    }

    public void deletSampleData() {
        mAppRepository.deleteSampleData();
    }
}
