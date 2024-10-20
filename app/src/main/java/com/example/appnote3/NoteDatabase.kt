package com.example.appnote3

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var instance: NoteDatabase? = null
        private const val DB_NAME = "notes.db"

        fun getInstance(application: Application): NoteDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    application,
                    NoteDatabase::class.java,
                    DB_NAME
                ).build().also { instance = it }
            }
        }
    }

    abstract fun notesDao(): NotesDao
}
