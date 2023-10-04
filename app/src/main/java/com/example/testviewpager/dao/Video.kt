package com.example.testviewpager.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Video(
    @ColumnInfo(name = "id") val videoId: String,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "content") var content: String,
    @ColumnInfo(name = "thumbnail") val thumbnail: String
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Long = 0
}

