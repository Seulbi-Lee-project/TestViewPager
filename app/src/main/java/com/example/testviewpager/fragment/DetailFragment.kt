package com.example.testviewpager.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.testviewpager.MainActivity
import com.example.testviewpager.R
import com.example.testviewpager.databinding.FragmentDetailBinding
import com.example.testviewpager.model.VideoItems
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class DetailFragment : Fragment() {

    private val binding by lazy { FragmentDetailBinding.inflate(layoutInflater) }
    private lateinit var mContext: Context
    private lateinit var videoItems: VideoItems

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val id = it.getString("id") ?: ""
            val title = it.getString("title") ?: ""
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
            binding.detailContent.text = it.getString("content")

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.detailIsLike.setOnClickListener {
            binding.detailIsLike.setImageResource(R.drawable.ic_heart_fill)
            (mContext as MainActivity).addLikedItem(videoItems)
            
        }
        binding.detailBackBtn.setOnClickListener {
            Toast.makeText(requireContext(), "click", Toast.LENGTH_SHORT).show()
            val fragmentManager = requireActivity().supportFragmentManager
            val mFragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            mFragmentTransaction.remove(this)
            mFragmentTransaction.commit()
            this.onDestroy()
            this.onDetach()
        }
        return binding.root
    }
}