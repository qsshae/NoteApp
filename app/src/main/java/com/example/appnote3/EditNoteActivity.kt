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

    private var noteId: Int = 0 // ID редактируемой заметки

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Включаем полноэкранный режим с прозрачными системными элементами
        setContentView(R.layout.activity_edit_note)

        // Обрабатываем системные отступы для корневого элемента, чтобы адаптировать его под полноэкранный режим
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this).get(EditNoteActivityViewModel::class.java)

        // Наблюдаем за сигналом о необходимости закрытия экрана
        viewModel.getShouldCloseScreen().observe(this, Observer { shouldClose ->
            if (shouldClose) finish() // Закрываем активность, если сработал триггер
        })

        initViews() // Инициализация View-элементов интерфейса

        val intent = intent
        noteId = intent.getIntExtra("noteId", -1) // Получаем ID заметки, если он передан

        // Наблюдаем за заголовком заметки
        viewModel.getHeaderLiveData().observe(this, Observer { header ->
            editTextHeader?.setText(header)
        })

        // Наблюдаем за текстом заметки
        viewModel.getTextLiveData().observe(this, Observer { text ->
            editTextNote?.setText(text)
        })

        // Если noteId равен -1, создаем новую заметку, иначе загружаем существующую
        if (noteId == -1) {
            viewModel.loadNewNote() // Инициализируем пустыми данными для новой заметки
        } else {
            viewModel.loadNote(noteId) // Загружаем существующую заметку из базы данных
        }

        // Обработка нажатия на кнопку сохранения заметки
        btnSaveNote?.setOnClickListener {
            saveNote()
        }
    }

    // Инициализация View-элементов интерфейса
    private fun initViews() {
        editTextNote = findViewById(R.id.editTextNote)
        btnSaveNote = findViewById(R.id.buttonSaveNote)
        editTextHeader = findViewById(R.id.editTextHeader)
        snackbarView = findViewById(android.R.id.content)
    }

    // Логика сохранения заметки
    private fun saveNote() {
        val updatedNoteText = editTextNote?.text.toString() // Получаем текст заметки
        val headerText = editTextHeader?.text.toString() // Получаем заголовок заметки
        val note = if (noteId != -1) {
            Note(noteId, headerText, updatedNoteText) // Обновляем существующую заметку
        } else {
            Note(headerText, updatedNoteText) // Создаем новую заметку
        }

        // Если текст заметки пустой, показываем Snackbar с сообщением об ошибке
        if (updatedNoteText.isEmpty()) {
            Snackbar.make(snackbarView!!, R.string.error_field_empty, Snackbar.LENGTH_LONG).show()
        } else {
            viewModel.saveNote(note) // Сохраняем заметку через ViewModel
        }
    }
}