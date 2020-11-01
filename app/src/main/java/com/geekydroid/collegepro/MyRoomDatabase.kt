package com.geekydroid.collegepro

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class MyRoomDatabase : RoomDatabase() {

    abstract fun NoteDao(): NoteDao?

    companion object {
        private var INSTANCE: MyRoomDatabase? = null

        fun get_instance(context: Context): MyRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(this)
                {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MyRoomDatabase::class.java,
                        "mydb.db"
                    ).build()

                    return INSTANCE
                }
            }
            return INSTANCE
        }
    }
}