package com.example.android.kotlinhabittracker

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private var arrayFragments: ArrayList<RecyclerFragment> = arrayListOf()

    fun addFragment(fragment: RecyclerFragment) {
        arrayFragments.add(fragment)
    }

    fun notify(habit: Habit) {
        for (item in arrayFragments) {
            if ((item.arguments?.getBoolean((IS_USEFUL)) == true && habit.type == "Useful")
                || (item.arguments?.getBoolean((IS_USEFUL)) == false && habit.type == "Harmful")
            ) {
                item.update(habit)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager2.offscreenPageLimit = 2
        viewPager2.adapter = PagerAdapter2(this)
        TabLayoutMediator(
            pagerTabLayout as TabLayout,
            viewPager2 as ViewPager2
        ) { tab, position ->
            tab.text = "FRAGMENT ${position + 1}"
        }.attach()
    }
}



