package com.example.testviewpager.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import com.example.testviewpager.R
import com.example.testviewpager.dao.Video
import com.example.testviewpager.dao.VideoDatabase
import com.example.testviewpager.databinding.FragmentDetailBinding
import com.example.testviewpager.model.VideoItems
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DetailFragment : Fragment() {

    private val binding by lazy { FragmentDetailBinding.inflate(layoutInflater) }
    private lateinit var mContext: Context
    private lateinit var videoItems: VideoItems
    private lateinit var database: VideoDatabase

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            database = VideoDatabase.getInstance(requireContext())!!

            val id = it.getString("id") ?: ""
            val title = it.getString("title") ?: ""
            val content = it.getString("content") ?: ""
            val thumbnail = it.getString("thumbnail") ?: ""
            videoItems = VideoItems(id, "", title, "", true)
            val recipeViewLinearLayout = binding.detailLinear
            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.bottomMargin = 30
            val ypv = YouTubePlayerView(requireContext())
            ypv.layoutParams = params
            recipeViewLinearLayout.addView(ypv)
            lifecycle.addObserver(ypv)
            ypv.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(id, 0f)
                }
            })
            binding.detailTitle.text = title
            binding.detailContent.text = content
            binding.detailIsLike.setOnClickListener {
                binding.detailIsLike.setImageResource(R.drawable.ic_heart_fill)
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        database.contactDao().insertVideo(Video(id, title, content, thumbnail))
                    }
                }
//                (mContext as MainActivity).addLikedItem(videoItems)
//                val main = MainActivity()
//                val array = intArrayOf()
//                var size = array.size
            }
        }
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.detailBackBtn.setOnClickListener {
            Toast.makeText(requireContext(), "click", Toast.LENGTH_SHORT).show()
            val fragmentManager = requireActivity().supportFragmentManager
            val mFragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            mFragmentTransaction.run {
                setCustomAnimations(
                    R.drawable.transition_fade_in,
                    R.drawable.transition_fade_out,
                    R.drawable.transition_fade_in,
                    R.drawable.transition_fade_out
                )
                addToBackStack(fragmentManager.backStackEntryCount.toString())
//                commit()
            }
            mFragmentTransaction.remove(this)
            mFragmentTransaction.commit()
            this.onDestroy()
            this.onDetach()

        }
        return binding.root
    }
}