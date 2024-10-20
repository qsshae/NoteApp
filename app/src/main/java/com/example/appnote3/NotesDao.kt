package com.example.appnote3

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes")
    fun getNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE id = :id")
    fun findById(id: Int): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(note: Note)

    @Query("DELETE FROM notes WHERE id = :id")
    fun remove(id: Int)
}
