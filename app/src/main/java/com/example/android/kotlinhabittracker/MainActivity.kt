package com.example.android.kotlinhabittracker

import android.os.Build
import android.os.Bundle
//import android.support.v4.widget.DrawerLayout
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var arrayFragments: ArrayList<RecyclerFragment> = arrayListOf()
//    private val drawerToggle = ActionBarDrawerToggle(this, R.id.drawerLayout,
//    R.string.drawer_open, R.string.drawer_close)
 private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var fragment: Fragment

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

//        drawer = findViewById(R.id.drawerLayout)
//        toggle = ActionBarDrawerToggle(this, drawer,
//            R.string.drawer_open, R.string.drawer_close)
//
//        drawer.addDrawerListener(toggle)
//        navView.setNavigationItemSelectedListener (this)

//        drawerLayout.addDrawerListener(drawerToggle)
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
            R.string.drawer_open, R.string.drawer_close
        )

        drawer.addDrawerListener(toggle)
        navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
  //      var fragment: Fragment? = RecyclerFragment.newInstance(true)
  //    var fragment: Fragment? = DescriptionFragment()
        when (item.itemId) {
            R.id.nav_screen -> fragment = RecyclerFragment()


            R.id.nav_info -> fragment = DescriptionFragment()
        }
 //       if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit()
  //      }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}





