package com.example.appnote3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    private var notes: List<Note> = ArrayList()
    private var OnNoteClickListener: OnClickListener? = null

    fun setOnNoteClickListener(onNoteClickListener: OnClickListener) {
        OnNoteClickListener = onNoteClickListener
    }

    fun getNotes(): List<Note> {
        return notes // Возвращаем существующий список
    }

    fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged() // Информируем адаптер об обновлении данных
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: NotesViewHolder, position: Int) {
        val note = notes[position]
        viewHolder.textViewNote.text = """
            ${note.header}
            ${note.text}
            """.trimIndent()

        viewHolder.itemView.setOnClickListener {
            OnNoteClickListener?.onNoteClick(note) // Используем более чистый способ
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val textViewNote: TextView = itemView.findViewById(R.id.textViewNote)
    }

    interface OnClickListener {
        fun onNoteClick(note: Note) // Убираем вопросительный знак
    }
}
