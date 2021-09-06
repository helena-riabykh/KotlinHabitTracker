package com.example.android.kotlinhabittracker

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.card_view.view.*

/**
 * Created by Anton on 15.03.2021.
 */
class ViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(habit: Habit) {
        containerView.habit_name.text = habit.name
        containerView.habit_description.text = habit.description
        containerView.habit_priority.text = habit.priority
        containerView.habit_type.text = habit.type
        containerView.habit_numberOfRuns.text = habit.numberOfRuns
        containerView.habit_frequencyOfExecution.text = habit.frequencyOfExecution
    }
}