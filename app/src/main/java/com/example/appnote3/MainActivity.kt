package com.example.appnote3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerViewNotes: RecyclerView // RecyclerView для отображения списка заметок
    private lateinit var btnAddNote: FloatingActionButton // Кнопка для добавления новой заметки
    private lateinit var textViewNoNotes: TextView // Для отображения информации об отсутствии заметок

    private lateinit var notesAdapter: NotesAdapter // Адаптер для управления отображением заметок

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Включаем полноэкранный режим с прозрачными системными элементами
        setContentView(R.layout.activity_main)

        // Обрабатываем системные отступы для editTextNote, чтобы адаптировать его под полноэкранный режим
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.editTextNote)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        initViews() // Инициализация View-элементов интерфейса

        notesAdapter = NotesAdapter() // Создание адаптера для заметок
        notesAdapter.setOnNoteClickListener(object : NotesAdapter.OnClickListener {
            override fun onNoteClick(note: Note) {
                val intent = Intent(this@MainActivity, EditNoteActivity::class.java)
                intent.putExtra("noteId", note.id) // Передаем ID заметки в следующую активность
                startActivity(intent)
            }
        })

        recyclerViewNotes.adapter = notesAdapter // Установка адаптера в RecyclerView

        // Наблюдаем за изменениями списка заметок через ViewModel
        viewModel.getNotes().observe(this, Observer { notes ->
            notesAdapter.setNotes(notes)
            if (notes.isEmpty()) {
                // Если список пуст, показываем сообщение "Нет заметок"
                textViewNoNotes.visibility = View.VISIBLE
                recyclerViewNotes.visibility = View.GONE
            } else {
                // Если заметки есть, отображаем список и скрываем сообщение
                textViewNoNotes.visibility = View.GONE
                recyclerViewNotes.visibility = View.VISIBLE
                notesAdapter.setNotes(notes) // Обновляем адаптер
            }
        })

        // Добавляем поддержку удаления заметок свайпом
        val itemTouchHelper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val note = notesAdapter.getNotes()[position]
                viewModel.remove(note) // Удаляем заметку через ViewModel
            }
        })

        itemTouchHelper.attachToRecyclerView(recyclerViewNotes) // Привязка ItemTouchHelper к RecyclerView

        // Обработка нажатия на кнопку добавления новой заметки
        btnAddNote.setOnClickListener {
            val intent = Intent(this@MainActivity, EditNoteActivity::class.java)
            startActivity(intent)
        }
    }

    // Инициализация View-элементов
    private fun initViews() {
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes)
        btnAddNote = findViewById(R.id.buttonAddNote)
        textViewNoNotes = findViewById(R.id.textViewNoNotes)
    }
}