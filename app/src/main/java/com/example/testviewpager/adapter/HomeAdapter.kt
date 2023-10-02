package com.example.testviewpager.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testviewpager.R
import com.example.testviewpager.databinding.ItemVideoBinding
import com.example.testviewpager.fragment.DetailFragment
import com.example.testviewpager.model.VideoItems

class HomeAdapter(val dataList: MutableList<VideoItems>) :
    RecyclerView.Adapter<HomeAdapter.Holder>() {

    inner class Holder(val binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.title
        val thumbnail = binding.videoThumbnail
    }

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.Holder {
        val binding = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
            val bundle = Bundle()
            val detailFragment = DetailFragment()
            bundle.putString("id", dataList[position].videoUri)
            bundle.putString("title", dataList[position].title)
            bundle.putString("content", dataList[position].content)
            bundle.putString("uri", dataList[position].videoUri)
            detailFragment.arguments = bundle

            val fragmentManager = (it.context as AppCompatActivity).supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.home_frame, detailFragment)
                .addToBackStack(null)
                .commit()
        }

        Glide.with(holder.binding.root).load(dataList[position].thumbnails)
            .into(holder.thumbnail)
        holder.title.text = dataList[position].title
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}