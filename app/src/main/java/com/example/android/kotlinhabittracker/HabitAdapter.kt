package com.example.android.kotlinhabittracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Anton on 15.03.2021.
 */
class HabitAdapter(
    private var habitList: ArrayList<Habit>,
) : RecyclerView.Adapter<ViewHolder>() {

    private lateinit var listener: Listener

    interface Listener {
        fun onClick(position: Int)
    }

    @JvmName("setListener1")
    fun setListener(listener: Listener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.card_view, parent, false) as CardView)
    }

    override fun getItemCount(): Int = habitList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(habitList[position])
        val cardView = holder.containerView
        cardView.setOnClickListener {
            listener.onClick(position)
        }
    }

    fun setData(list: ArrayList<Habit>) {
        habitList = list
    }
}