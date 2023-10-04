package com.example.testviewpager.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testviewpager.MainActivity
import com.example.testviewpager.R
import com.example.testviewpager.adapter.MyVideoAdapter
import com.example.testviewpager.dao.Video
import com.example.testviewpager.dao.VideoDatabase
import com.example.testviewpager.databinding.FragmentMyVideoBinding
import com.example.testviewpager.model.VideoItems
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyVideoFragment : Fragment() {

    private val binding by lazy { FragmentMyVideoBinding.inflate(layoutInflater) }
    private var datalist = mutableListOf<VideoItems>()
    private lateinit var database: VideoDatabase

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
        database = VideoDatabase.getInstance(requireContext())!!
        var data: List<Video>
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                data = database.contactDao().getAll()
                val adapter = MyVideoAdapter(data)
                binding.myVideoRecycler.adapter = adapter
                binding.myVideoRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
                adapter.notifyDataSetChanged()
            }
        }
        return binding.root
    }

    @SuppressLint("ResourceType")
    fun changeFragment(fragment: Fragment) {
        (view?.context as AppCompatActivity).supportFragmentManager.beginTransaction().run {
            setCustomAnimations(
                R.drawable.transition_fade_in,
                R.drawable.transition_fade_out,
                R.drawable.transition_fade_in,
                R.drawable.transition_fade_out
            )
            addToBackStack((view?.context as AppCompatActivity).supportFragmentManager.backStackEntryCount.toString())
            commit()
        }
    }
}