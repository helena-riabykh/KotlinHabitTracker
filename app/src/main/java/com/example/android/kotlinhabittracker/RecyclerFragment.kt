package com.example.android.kotlinhabittracker

import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_recycler.*

const val IS_USEFUL = "isUseful"

class RecyclerFragment() : Fragment() {
    private var result: ArrayList<Habit> = arrayListOf()
    private lateinit var adapter: HabitAdapter

    companion object {
        @JvmStatic
        fun newInstance(isUseful: Boolean) =
            RecyclerFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(IS_USEFUL, isUseful)
                }
            }

//        fun newInstance(): RecyclerFragment {
//           return RecyclerFragment()
//        }
    }

    fun update(newHabit: Habit) {
        result.add(newHabit)
        adapter.notifyDataSetChanged()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            context.addFragment(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            result = savedInstanceState.getParcelableArrayList("result")!!
        }
        adapter = HabitAdapter(result)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

        float_button.setOnClickListener {
            val habitFragment = HabitFragment()
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.container, habitFragment)
                ?.addToBackStack(null)
                ?.commit()
        }
    }

    override fun onSaveInstanceState(saveInstanceState: Bundle) {
        super.onSaveInstanceState(saveInstanceState)
        saveInstanceState.putParcelableArrayList("result", result)
    }
}
