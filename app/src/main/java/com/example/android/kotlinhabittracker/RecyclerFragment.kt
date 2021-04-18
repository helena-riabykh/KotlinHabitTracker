package com.example.android.kotlinhabittracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_recycler.*
import java.util.*
import kotlin.collections.ArrayList

private const val ARG_PARAM1 = "isUseful"
private const val HABIT_ARRAY = "habitArray"
private const val HABIT_OBJECT = "habitObject"

class RecyclerFragment : Fragment() {

    companion object {
        var mHabitList: ArrayList<Habit> = arrayListOf()
        var adapter: HabitAdapter? = HabitAdapter(mHabitList)

        @JvmStatic
        fun newInstance(isUseful: Boolean) =
            RecyclerFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(ARG_PARAM1, isUseful)
                }
            }

        @JvmStatic
        fun newInstance(habit: Habit) =
            RecyclerFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(HABIT_OBJECT, habit)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            mHabitList = savedInstanceState.getParcelableArrayList(HABIT_ARRAY)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        if (savedInstanceState == null) {
            arguments?.let {
                val habit = it.getParcelable<Habit>(HABIT_OBJECT)
                if (habit != null) {
                    mHabitList.add(habit)
                    adapter?.notifyDataSetChanged()
                }
            }
        }
        float_button.setOnClickListener {
            val habitFragment = HabitFragment()
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, habitFragment)
                .commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.apply { putParcelableArrayList(HABIT_ARRAY, mHabitList) }
    }
}

