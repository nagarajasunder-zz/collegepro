package com.geekydroid.collegepro

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    var noteId: Int,
    @ColumnInfo(name = "Title")
    var title: String,
    @ColumnInfo(name = "Desc")
    var desc: String,
    @ColumnInfo(name = "created_at")
    var created: String,
    @ColumnInfo(name = "to_notify")
    var to_notify: Boolean,
    @ColumnInfo(name = "note_time")
    var note_time: Long,
    @ColumnInfo(name = "completed_at")
    var completed_at: Long,
    @ColumnInfo(name = "category")
    var category: String
)