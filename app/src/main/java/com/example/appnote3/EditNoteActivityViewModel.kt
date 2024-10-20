package com.example.appnote3

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class EditNoteActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val noteDatabase: NoteDatabase = NoteDatabase.getInstance(application)
    private val shouldCloseScreen = MutableLiveData<Boolean>()
    private val headerLiveData = MutableLiveData<String>()
    private val textLiveData = MutableLiveData<String>()

    fun getShouldCloseScreen(): LiveData<Boolean> {
        return shouldCloseScreen
    }

    fun saveNote(note: Note) {
        Thread {
            noteDatabase.notesDao().add(note)
            shouldCloseScreen.postValue(true)
        }.start()
    }

    fun getHeaderLiveData(): LiveData<String> {
        return headerLiveData
    }

    fun getTextLiveData(): LiveData<String> {
        return textLiveData
    }

    fun loadNote(noteId: Int) {
        Thread {
            val note = noteDatabase.notesDao().findById(noteId)
            headerLiveData.postValue(note.header)
            textLiveData.postValue(note.text)
        }.start()
    }

    fun loadNewNote() {
        // Устанавливаем пустые значения
        headerLiveData.value = ""
        textLiveData.value = ""
    }
}
