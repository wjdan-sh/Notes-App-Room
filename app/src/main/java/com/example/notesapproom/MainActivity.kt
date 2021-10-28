package com.example.notesapproom

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var RC: RecyclerView
    private lateinit var ed: EditText
    private lateinit var btn: Button
    private lateinit var Notes: List<Notes>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Notes= listOf()

        ed = findViewById(R.id.ed)
        btn = findViewById(R.id.btn)
        RC = findViewById(R.id.rv)

        getNotes()

        NotesDatabase.getInstance(applicationContext)

        btn.setOnClickListener {

            val note1 = ed.text.toString()
            val s = Notes(0, note1)

            NotesDatabase.getInstance(applicationContext).NotesDao().insertNote(s)

            Toast.makeText(applicationContext, "data saved successfully! ", Toast.LENGTH_SHORT).show()

            ed.text.clear()
            ed.clearFocus()
            getNotes()

            }
        }

    fun updateRC(){
        RC.adapter = RVAdapter(this, Notes)
        RC.layoutManager = LinearLayoutManager(this)
        RC.scrollToPosition(Notes.size-1)
    }
    fun getNotes(){

    Notes = NotesDatabase.getInstance(applicationContext).NotesDao().getAllNotes()
    updateRC()

     }

    fun update(NoteId:Int , Note:String){

     NotesDatabase.getInstance(applicationContext).NotesDao().updateNote( Notes( NoteId , Note ))

     Toast.makeText(this, "data updated! "  , Toast.LENGTH_SHORT).show()
     getNotes()

    }
    fun delete(note: Notes) {

        NotesDatabase.getInstance(applicationContext).NotesDao().deleteNote(note)
            getNotes()

     }
    fun Dialog(Note1: Notes){
        val dialogBuilder = AlertDialog.Builder(this)
        val ed = EditText(this)

        val NoteId = Note1.id
        ed.hint = "Enter new text"
        dialogBuilder
            .setCancelable(false)
            .setPositiveButton("Save", DialogInterface.OnClickListener {

                    dialog, id->
                val Note = ed.text.toString()
                update(NoteId,Note)

            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Update Note")
        alert.setView(ed)
        alert.show()
    }



}

