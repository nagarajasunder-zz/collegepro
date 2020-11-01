package com.geekydroid.collegepro

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "NewNote"

class NewNote : AppCompatActivity() {

    //views
    private lateinit var title: TextInputLayout
    private lateinit var desc: TextInputLayout
    private lateinit var add_note: Button
    private lateinit var time: TextView

    //vars
    private lateinit var watcher: TextWatcher
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)

        setUI()

        add_note.setOnClickListener {
            var note_title = title.editText!!.text.toString()
            var note_desc = title.editText!!.text.toString()

            var note = Note(
                0,
                note_title,
                note_desc,
                SimpleDateFormat("dd/MM/yyyy").format(Date()),
                false,
                0,
                0,
                "Todo"
            )

            add_new_note(note)
        }

        time.setOnClickListener {
            open_timepicker()
        }

    }

    private fun open_timepicker() {
        Log.d(TAG, "open_timepicker: called")
        val dialog = TimePickerDialog(
            this@NewNote,
            { p0, p1, p2 -> },
            Calendar.getInstance().get(Calendar.HOUR),
            Calendar.getInstance().get(Calendar.MINUTE),
            true
        )
        dialog.show()

    }

    private fun add_new_note(note: Note) {
        GlobalScope.launch(Dispatchers.IO) {
            MyRoomDatabase.get_instance(applicationContext)!!.NoteDao()!!.add_note(note)
        }
    }


    private fun setUI() {
        title = findViewById(R.id.title)
        desc = findViewById(R.id.desc)
        add_note = findViewById(R.id.add_note)
        time = findViewById(R.id.time)
        time.text = SimpleDateFormat("HH:mm").format(Date())

        watcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (text.toString().trim().isNotEmpty()) {
                    add_note.visibility = View.VISIBLE
                } else {
                    add_note.visibility = View.INVISIBLE
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        }

        title.editText!!.addTextChangedListener(watcher)
        desc.editText!!.addTextChangedListener(watcher)

    }

}