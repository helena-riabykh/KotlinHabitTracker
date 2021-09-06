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

    fun editingHabit(habit: Habit, changeHabitType: Boolean, index: Int) {
        if (!changeHabitType) {
            habitArrayList[index] = habit
        } else {
            habitArrayList.removeAt(index)
            habitArrayList.add(habit)
        }
        mutableLiveData.value = habitArrayList
    }

    fun getHabitsLiveData(): LiveData<List<Habit>> {
        return mutableLiveData
    }

    fun getHabitArrayList1(): MutableList<Habit> {
        return habitArrayList
    }

    fun filterName(name: String?) {
        var result: MutableList<Habit> = arrayListOf()
        if (name == null || name == "") {
            result = habitArrayList
        } else
            for (habit in habitArrayList) {
                if (habit.name.contains(name)) {
                    result.add(habit)
                }
            }
        mutableLiveData.value = result
    }
}

