package com.example.testviewpager.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface VideoDao {
    @Query("SELECT * FROM video")
    fun getAll(): List<Video>

    @Query("SELECT * FROM video WHERE id IN (:videoIds)")
    fun loadAllByVideoIds(videoIds: ArrayList<String>): List<Video>

    @Insert
    fun insertAll(vararg videos: Video)

    @Insert
    fun insertVideo(video: Video) // 삽입

    @Update
    fun updateVideo(video: Video) // 수정

    @Delete
    fun delete(video: Video) // 삭제
}