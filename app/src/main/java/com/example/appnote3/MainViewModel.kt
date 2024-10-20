package com.example.appnote3

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val noteDatabase: NoteDatabase = NoteDatabase.getInstance(application)

    fun getNotes(): LiveData<List<Note>> {
        return noteDatabase.notesDao().getNotes()
    }

    fun remove(note: Note) {
        Thread {
            noteDatabase.notesDao().remove(note.id)
        }.start()
    }
}