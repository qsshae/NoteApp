package com.example.appnote3

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val header: String,
    val text: String
) {
    @Ignore
    constructor(header: String, text: String) : this(0, header, text)
}
