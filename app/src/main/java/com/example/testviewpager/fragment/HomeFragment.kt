package com.example.testviewpager.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testviewpager.adapter.HomeAdapter
import com.example.testviewpager.api.RetrofitApi
import com.example.testviewpager.api.YoutubeResponse
import com.example.testviewpager.databinding.FragmentHomeBinding
import com.example.testviewpager.model.VideoItems
import retrofit2.Call
import retrofit2.Response

class HomeFragment : Fragment() {

    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private var videoList: ArrayList<VideoItems> = ArrayList()
    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("TAG", "HomeFragment")
        val service = RetrofitApi.youtubeService
        service.getYoutubeVideo(apiKey = "AIzaSyB-hi0gpZmfY5A0fv_wOVf_q6l1L0N5Jz4")
            .enqueue(object : retrofit2.Callback<YoutubeResponse> {
                override fun onResponse(
                    call: Call<YoutubeResponse>,
                    response: Response<YoutubeResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.items?.forEach { item ->
                            val id = item.id
                            val title = item.snippet.title
                            val content = item.snippet.description
                            val thumbnails = item.snippet.thumbnails.default.url
                            videoList.add(
                                VideoItems(
                                    id,
                                    thumbnails,
                                    title,
                                    content,
                                    false
                                )
                            )
                        }
                    }

                    val adapter = HomeAdapter(videoList)
                    binding.homeRecyclerView.adapter = adapter
                    binding.homeRecyclerView.layoutManager = LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.HORIZONTAL,
                        true
                    )

                }

                override fun onFailure(call: Call<YoutubeResponse>, t: Throwable) {
                    Log.d("TAG", t.message.toString())
                }
            })

        return binding.root
    }
}