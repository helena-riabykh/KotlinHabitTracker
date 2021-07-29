package com.example.android.kotlinhabittracker

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Created by Anton on 09.06.2021.
 */
class Model() {
    private var habitArrayList: MutableList<Habit> = arrayListOf()
    private val mutableLiveData: MutableLiveData<List<Habit>> = MutableLiveData<List<Habit>>()
    var db = App.getInstance().getDatabase()
    var habitDao = db?.getHabitDao()

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
        habitDao?.insert(habit)
        mutableLiveData.value = habitDao?.getAll()
    }

    fun editingHabit(habit: Habit) {
        habitDao?.update(habit)
        mutableLiveData.value = habitDao?.getAll()
    }

    fun getMutableLiveData(): LiveData<List<Habit>> {
        mutableLiveData.value = habitDao?.getAll()
        return mutableLiveData
    }

    fun getHabitsLiveData(): LiveData<List<Habit>> {
        return mutableLiveData
    }

    fun getHabitArrayList1(): MutableList<Habit> {
        return habitArrayList
    }

    fun filterName(name: String?) {
        mutableLiveData.value = habitDao?.getAllWithNameLike(name)
    }
}

