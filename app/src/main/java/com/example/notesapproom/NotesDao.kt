package com.example.notesapproom


import androidx.room.*

@Dao
interface NotesDao {

    @Query("SELECT * FROM Notes ")
    fun getAllNotes(): List<Notes>

    @Insert
    fun insertNote(note: Notes)

    @Update
    fun updateNote(note: Notes)

    @Delete
    fun deleteNote(note:Notes)




}