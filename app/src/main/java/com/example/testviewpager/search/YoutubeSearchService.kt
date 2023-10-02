package com.example.testviewpager.search

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeSearchService {
    @GET("search")
    fun getYoutubeVideo(
        @Query("part") part: String = "snippet",
        @Query("maxResults") maxResults: Int = 10,
        @Query("q") search: String,
        @Query("key") apiKey: String
    ): Call<YoutubeSearchResponse>
}