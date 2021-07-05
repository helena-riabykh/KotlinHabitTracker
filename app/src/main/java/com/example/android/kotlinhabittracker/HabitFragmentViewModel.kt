package com.example.android.kotlinhabittracker

import androidx.lifecycle.ViewModel

/**
 * Created by Anton on 09.06.2021.
 */
class HabitFragmentViewModel: ViewModel() {
    private val model = Model.getInstance()

    fun notify(habit: Habit, position: Int, changeHabitType: Boolean, index: Int) {
        if (position == -1) {
            model.addHabit(habit)
        } else {
            model.editingHabit(position, habit, changeHabitType, index)
        }
    }
    fun notifyName(name: String?) {
        model.filterName(name)
    }
}