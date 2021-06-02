package com.example.android.kotlinhabittracker

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var arrayFragments: ArrayList<RecyclerFragment> = arrayListOf()
    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    var fragment: DescriptionFragment? = DescriptionFragment()
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

        setSupportActionBar(toolbar as Toolbar?)
        supportActionBar?.setTitle(R.string.app_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_done_white_24dp)

        viewPager2.offscreenPageLimit = 2
        viewPager2.adapter = PagerAdapter2(this)
        TabLayoutMediator(
            pagerTabLayout as TabLayout,
            viewPager2 as ViewPager2
        ) { tab, position ->
            tab.text = "FRAGMENT ${position + 1}"
        }.attach()

        drawer = findViewById(R.id.drawerLayout)
        toggle = ActionBarDrawerToggle(
            this, drawer,
            toolbar,
            R.string.drawer_open, R.string.drawer_close
        )

        drawer.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_screen -> {
                fragment?.let {
                    supportFragmentManager
                        .beginTransaction()
                        .remove(it)
                        .addToBackStack(null)
                        .commit()
                }
                viewPager2.offscreenPageLimit = 2
                viewPager2.adapter

                TabLayoutMediator(
                    pagerTabLayout as TabLayout,
                    viewPager2 as ViewPager2
                ) { tab, position ->
                    tab.text = "FRAGMENT ${position + 1}"
                }.attach()
            }

            R.id.nav_info -> {
                fragment?.let {
                    supportFragmentManager
                        .beginTransaction()
                        .add(R.id.container, it)
                        .addToBackStack(null)
                        .commit()
                }
            }
        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}





