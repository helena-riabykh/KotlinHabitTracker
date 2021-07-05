package com.example.android.kotlinhabittracker

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

/**
 * Created by Anton on 09.06.2021.
 */
class RecycleFragmentViewModel: ViewModel() {
    private val model = Model.getInstance()

    private val mutableLiveDataUseful: MutableLiveData<List<Habit>> = MutableLiveData<List<Habit>>()
    private val mutableLiveDataHarmful: MutableLiveData<List<Habit>> = MutableLiveData<List<Habit>>()

    fun getHarmful(): LiveData<List<Habit>> {
        model.getHabitsLiveData().observeForever { habits ->
            val result = ArrayList<Habit>()
            for (habit in habits) {
                if (habit.type == "Harmful") {
                    result.add(habit)
                }
            }
            mutableLiveDataHarmful.setValue(result)
        }
        return mutableLiveDataHarmful
    }

    fun getUseful(): LiveData<List<Habit>> {
        model.getHabitsLiveData().observeForever { habits ->
            val result = ArrayList<Habit>()
            for (habit in habits) {
                if (habit.type == "Useful") {
                    result.add(habit)
                }
            }
            mutableLiveDataUseful.setValue(result)
        }
        return mutableLiveDataUseful
    }

}