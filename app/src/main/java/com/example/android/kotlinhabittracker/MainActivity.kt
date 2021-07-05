package com.example.android.kotlinhabittracker

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.EditText
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.*
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_filter.*

const val OFFSCREEN_PAGER_LIMIT_DEFAULT = 2

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private var descriptionFragment: DescriptionFragment? = DescriptionFragment()
    private var strValue: String? = ""
    private lateinit var BottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var layoutBottomSheet: LinearLayout

    fun stateOfBottomSheetHidden() {
        BottomSheetBehavior.state = STATE_HIDDEN
    }

    fun stateOfBottomSheetCollapsed() {
        BottomSheetBehavior.state = STATE_COLLAPSED
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        layoutBottomSheet = findViewById(R.id.bottom_sheet_behavior_id)
        BottomSheetBehavior = from(layoutBottomSheet)

  //      val description = findViewById<EditText>(R.id.description)
        description.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                strValue = description.text.toString()
                if (strValue != null) {
                    val habitFragmentViewModel = HabitFragmentViewModel()
                    habitFragmentViewModel.notifyName(strValue)
                }
            }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
                //Прописываем то, что надо выполнить до изменений
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                //только что заменены
            }
        })

        setSupportActionBar(toolbar)
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
                descriptionFragment?.let {
                    supportFragmentManager
                        .beginTransaction()
                        .remove(it)
                        .addToBackStack(null)
                        .commit()
                }
                viewPager2.adapter

                TabLayoutMediator(
                    pagerTabLayout as TabLayout,
                    viewPager2 as ViewPager2
                ) { tab, position ->
                    tab.text = "FRAGMENT ${position + 1}"
                }.attach()
            }

            R.id.nav_info -> {
                descriptionFragment?.let {
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






