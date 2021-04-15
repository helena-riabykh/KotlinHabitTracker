package com.example.android.kotlinhabittracker

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        var mHabitList: ArrayList<Habit> = arrayListOf()
        var adapter: HabitAdapter = HabitAdapter(mHabitList)
        private const val HABIT_ARRAY = "habitArray"
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            mHabitList =
                savedInstanceState.getParcelableArrayList<Habit>(HABIT_ARRAY) as ArrayList<Habit>
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        if (savedInstanceState == null) {
            val habit: Habit? = intent.getParcelableExtra(HabitActivity.HABIT_EXTRA) as? Habit

            if (habit != null) {
                mHabitList.add(habit)
                adapter.notifyDataSetChanged()
            }
        }
    }

    fun onClickDone(v: View?) {
        val intent = Intent(this, HabitActivity::class.java)
        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.apply { putParcelableArrayList(HABIT_ARRAY, mHabitList) }
    }
}



