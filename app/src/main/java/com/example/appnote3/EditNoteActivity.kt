package com.example.appnote3

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar

class EditNoteActivity : AppCompatActivity() {
    private var editTextNote: EditText? = null // Поле для редактирования текста заметки
    private var editTextHeader: EditText? = null
    private var btnSaveNote: Button? = null // Кнопка для сохранения изменений
    private var snackbarView: View? = null // Для уведомления о пустой заметке

    private lateinit var viewModel: EditNoteActivityViewModel

    private val handler = Handler(Looper.getMainLooper()) // Обработчик для выполнения кода в главном потоке

    private var noteId: Int = 0 // ID редактируемой заметки

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_note)

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

            //  val database = NoteDatabase.getInstance(application)
//        val repository = NoteRepository(database)
//        val factory = EditNoteViewModelFactory(application, repository)

        // Инициализация ViewModel с фабрикой
        viewModel = ViewModelProvider(this).get(EditNoteActivityViewModel::class.java)

        viewModel.getShouldCloseScreen().observe(this, Observer { shouldClose ->
            if (shouldClose) finish()
        })

        initViews()

        val intent = intent
        noteId = intent.getIntExtra("noteId", -1)

        viewModel.getHeaderLiveData().observe(this, Observer { header ->
            editTextHeader?.setText(header)
        })

        viewModel.getTextLiveData().observe(this, Observer { text ->
            editTextNote?.setText(text)
        })

        if (noteId == -1) {
            viewModel.loadNewNote() // Инициализируем пустыми данными
        } else {
            viewModel.loadNote(noteId) // Загружаем существующую заметку из базы данных
        }

        btnSaveNote?.setOnClickListener {
            saveNote()
        }
    }

    private fun initViews() {
        editTextNote = findViewById(R.id.editTextNote)
        btnSaveNote = findViewById(R.id.buttonSaveNote)
        editTextHeader = findViewById(R.id.editTextHeader)
        snackbarView = findViewById(android.R.id.content)
    }

    private fun saveNote() {
        val updatedNoteText = editTextNote?.text.toString()
        val headerText = editTextHeader?.text.toString()
        val note = if (noteId != -1) {
            Note(noteId, headerText, updatedNoteText)
        } else {
            Note(headerText, updatedNoteText)
        }

        if (updatedNoteText.isEmpty()) {
            Snackbar.make(snackbarView!!, R.string.error_field_empty, Snackbar.LENGTH_LONG).show()
        } else {
            viewModel.saveNote(note)
        }
    }
}
