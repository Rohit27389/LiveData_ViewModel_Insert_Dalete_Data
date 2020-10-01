package com.example.activity_viewmodel_livedata.database;

import android.content.Context;
import android.util.Log;

import com.example.activity_viewmodel_livedata.utils.SampleDataProvider;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;

public class AppRepository {

    private AppDatabase mAppDatabase;
    public LiveData<List<NoteEntity>> repoNotes;
    public static final String TAG = "AppRepository";

    public static AppRepository ourInstance;
    private Executor mExecutor = Executors.newSingleThreadExecutor();

    public static AppRepository getInstance(Context context) {
        return ourInstance = new AppRepository(context);
    }

    private AppRepository(Context context) {
        mAppDatabase = AppDatabase.getInstance(context);
        repoNotes = getAllNotes();
    }

    public void addSampleData() {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {

                mAppDatabase.noteDao().insertAll(SampleDataProvider.getSampleData());
            }
        });
    }

    private LiveData<List<NoteEntity>> getAllNotes() {
        return mAppDatabase.noteDao().getAllNote();
    }

    public void deleteSampleData() {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                int delete_Id = mAppDatabase.noteDao().deleteAllNotes();
                Log.d(TAG, "Delete_Note: " + delete_Id);
            }
        });
    }
}
