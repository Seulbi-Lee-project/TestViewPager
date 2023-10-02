package com.example.testviewpager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testviewpager.databinding.ItemVideoBinding
import com.example.testviewpager.model.VideoItems

class MyVideoAdapter(val dataList: MutableList<VideoItems>) :
    RecyclerView.Adapter<MyVideoAdapter.Holder>() {

    inner class Holder(val binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.title
        val thumbnail = binding.videoThumbnail
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        Glide.with(holder.binding.root).load(dataList[position].thumbnails)
            .into(holder.thumbnail)
        holder.title.text = dataList[position].title
    }
}