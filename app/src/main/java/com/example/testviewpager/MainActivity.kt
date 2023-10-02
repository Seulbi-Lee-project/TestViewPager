package com.example.testviewpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testviewpager.adapter.TabPagerAdapter
import com.example.testviewpager.databinding.ActivityMainBinding
import com.example.testviewpager.model.VideoItems
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    var likedItems: ArrayList<VideoItems> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager

        // adapter 준비 및 연결
        val adapter = TabPagerAdapter(this)
        viewPager.adapter = adapter

        val tabTitles = listOf<String>("Home", "Search", "My Video")

        // TabLayout, ViewPager 연결
        TabLayoutMediator(
            tabLayout, viewPager
        ) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    fun addLikedItem(item: VideoItems) {
        if (!likedItems.contains(item)) {
            likedItems.add(item)
        }
    }
}