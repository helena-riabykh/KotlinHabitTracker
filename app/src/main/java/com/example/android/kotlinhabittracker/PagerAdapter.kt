package com.example.android.kotlinhabittracker

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Created by Anton on 20.04.2021.
 */
class PagerAdapter2(activity: MainActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> RecyclerFragment.newInstance(true)
        else -> RecyclerFragment.newInstance(false)
    }
}