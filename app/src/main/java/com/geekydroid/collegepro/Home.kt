package com.geekydroid.collegepro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "Home"

class Home : AppCompatActivity() {

    //views
    private lateinit var add: FloatingActionButton
    private lateinit var recyclerView: RecyclerView

    //vars
    private lateinit var adapter: NotesAdapter
    private lateinit var list: List<Note>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setUI()
        fetch_notes()
        add.setOnClickListener {
            startActivity(Intent(applicationContext, NewNote::class.java))
        }
    }

    private fun fetch_notes() {
        GlobalScope.launch(Dispatchers.IO) {
            list = MyRoomDatabase.get_instance(applicationContext)?.NoteDao()!!.get_all_notes()
            adapter = NotesAdapter(list)
            Log.d(TAG, "fetch_notes: ${list.size}")
            withContext(Dispatchers.Main)
            {
                Log.d(TAG, "fetch_notes: main thread")
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }

    }

    private fun setUI() {
        add = findViewById(R.id.add)
        recyclerView = findViewById(R.id.recycler_view)
        list = ArrayList()
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL)

    }

    override fun onResume() {
        super.onResume()
        fetch_notes()
    }


}