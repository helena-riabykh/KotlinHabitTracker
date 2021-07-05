package com.example.android.kotlinhabittracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.ArrayList

/**
 * Created by Anton on 09.06.2021.
 */
class Model() {
    var habitArrayList: MutableList<Habit> = arrayListOf()
    private val mutableLiveData: MutableLiveData<List<Habit>> = MutableLiveData<List<Habit>>()
//    var result: MutableList<Habit> = arrayListOf()
    companion object {
        private var instance: Model? = null

        @JvmStatic
        fun getInstance(): Model {
            if (instance == null) {
                instance = Model()
            }
            return instance as Model
        }
    }

    fun addHabit(habit: Habit) {
        habitArrayList.add(habit)
        mutableLiveData.value = habitArrayList
    }

    fun editingHabit(position: Int, habit: Habit, changeHabitType: Boolean, index: Int) {
        if (!changeHabitType) {
            //           habitArrayList.removeAt(position)
            habitArrayList.set(position, habit)
        } else{
//            val index = habitArrayList.indexOf(habit)
            habitArrayList.removeAt(index)
        habitArrayList.add(habit)
    }
        mutableLiveData.value = habitArrayList
    }

    fun getHabitsLiveData(): LiveData<List<Habit>> {
        return mutableLiveData
    }

    @JvmName("getHabitArrayList1")
    fun getHabitArrayList(): MutableList<Habit> {
        return habitArrayList
    }

    fun filterName(name: String?) {
        var result : MutableList<Habit> = arrayListOf()
        if (name == null) {
                result = habitArrayList
        }else
            for (habit in habitArrayList) {
            if (habit.name == name) {
                result.add(habit)
            }
        }
        mutableLiveData.value = result
    }
}