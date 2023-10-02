package com.example.testviewpager.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.testviewpager.fragment.HomeFragment
import com.example.testviewpager.fragment.MyVideoFragment
import com.example.testviewpager.fragment.SearchFragment


class TabPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private var fragments =
        arrayOf<Fragment>(HomeFragment(), SearchFragment(), MyVideoFragment())
    private val fmIds = fragments.map { it.hashCode().toLong() }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun getItemId(position: Int): Long {
        return fragments[position].hashCode().toLong()
    }

    override fun containsItem(itemId: Long): Boolean {
        return fmIds.contains(itemId)
    }
}