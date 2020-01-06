package com.example.mynote.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.mynote.Nota;

@Database(entities = {Nota.class}, version = 1)
public abstract class NotaDatabase extends RoomDatabase {
    public abstract NotaDao getNotaDao();
}