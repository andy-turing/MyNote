package com.example.mynote.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mynote.Nota;

import java.util.List;

@Dao
public interface NotaDao {

    @Query("SELECT * FROM nota")
    List<Nota> getNotas();

    @Query("SELECT * FROM nota WHERE mId LIKE :uuid")
    Nota getNota(String uuid);

    @Insert
    void addNota(Nota book);

    @Delete
    void deleteNota(Nota book);

    @Update
    void updateNota(Nota book);
}