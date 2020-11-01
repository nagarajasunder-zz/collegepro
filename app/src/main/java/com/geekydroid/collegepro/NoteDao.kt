package com.geekydroid.collegepro

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {

    @Insert
    suspend fun add_note(note: Note)

    @Query("SELECT * FROM Notes")
    suspend fun get_all_notes():List<Note>
}