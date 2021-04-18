package com.example.android.kotlinhabittracker

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val recyclerFragment = RecyclerFragment.newInstance(isUseful = true)
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, recyclerFragment)
                    .commit()
            }
        }
}




