package com.example.testviewpager.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testviewpager.adapter.SearchAdapter
import com.example.testviewpager.api.RetrofitApi
import com.example.testviewpager.databinding.FragmentSearchBinding
import com.example.testviewpager.model.VideoItems
import com.example.testviewpager.search.YoutubeSearchResponse
import retrofit2.Call
import retrofit2.Response


class SearchFragment : Fragment() {

    private val binding by lazy { FragmentSearchBinding.inflate(layoutInflater) }
    private lateinit var mContext: Context
    private var videoList: ArrayList<VideoItems> = ArrayList()

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

        binding.searchBtn.setOnClickListener {
            var search = binding.searchEdit.text.toString()
            if (search != "") {
                val service = RetrofitApi.youtubeSearchService
                service.getYoutubeVideo(
                    search = search,
                    apiKey = "AIzaSyB-hi0gpZmfY5A0fv_wOVf_q6l1L0N5Jz4"
                )
                    .enqueue(object : retrofit2.Callback<YoutubeSearchResponse> {
                        override fun onResponse(
                            call: Call<YoutubeSearchResponse>,
                            response: Response<YoutubeSearchResponse>
                        ) {
                            if (response.isSuccessful) {
                                response.body()?.items?.forEach { item ->
                                    val id = item.id.videoId
                                    val title = item.snippet.title
                                    val content = item.snippet.description
                                    val thumbnails = item.snippet.thumbnails.default.url
                                    videoList.add(
                                        VideoItems(id, thumbnails, title, content, false)
                                    )
                                }

                                var adapter: SearchAdapter = SearchAdapter(videoList)
                                binding.searchRecyclerView.adapter = adapter
                                binding.searchRecyclerView.layoutManager =
                                    GridLayoutManager(requireContext(), 2)
                            }
                        }

                        override fun onFailure(call: Call<YoutubeSearchResponse>, t: Throwable) {
                            Log.d("TAG", t.message.toString())
                        }
                    })
            }
        }
        return binding.root
    }
}