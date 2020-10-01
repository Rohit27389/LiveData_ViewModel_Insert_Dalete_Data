package com.example.activity_viewmodel_livedata.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {NoteEntity.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAMe = "notesdatabase.db";

    public abstract NoteDao noteDao();

    public static volatile AppDatabase instance;

    private static final Object LOCk = new Object();

    public static AppDatabase getInstance(Context context) {

        if (instance == null) {
            synchronized (LOCk) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DATABASE_NAMe).fallbackToDestructiveMigration().build();

                }
            }
        }
        return instance;
    }

}
