package com.example.testviewpager.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testviewpager.MainActivity
import com.example.testviewpager.adapter.MyVideoAdapter
import com.example.testviewpager.databinding.FragmentMyVideoBinding
import com.example.testviewpager.model.VideoItems

class MyVideoFragment : Fragment() {

    private val binding by lazy { FragmentMyVideoBinding.inflate(layoutInflater) }
    private var datalist = mutableListOf<VideoItems>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mainActivity = activity as MainActivity
        datalist.clear()
        datalist = mainActivity.likedItems
        val adapter = MyVideoAdapter(datalist)
        binding.myVideoRecycler.adapter = adapter
        binding.myVideoRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter.notifyDataSetChanged()
        return binding.root
    }
}