package com.example.testviewpager.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Video::class], version = 1)
abstract class VideoDatabase : RoomDatabase() {
    abstract fun contactDao(): VideoDao

    companion object {
        private var instance: VideoDatabase? = null

        @Synchronized
        fun getInstance(context: Context): VideoDatabase? {
            if (instance == null) {
                synchronized(VideoDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        VideoDatabase::class.java,
                        "MyVideo"
                    ).build()
                }
            }
            return instance
        }
    }
}