package com.example.android.kotlinhabittracker

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.kotlinhabittracker.HabitAdapter.Listener
import kotlinx.android.synthetic.main.fragment_recycler.*
import java.util.*

class RecyclerFragment() : Fragment() {
    private var result: ArrayList<Habit> = arrayListOf()
    private lateinit var adapter: HabitAdapter
    private lateinit var recycleFragmentViewModel: RecycleFragmentViewModel
    private lateinit var activity: MainActivity

    companion object {
        @JvmStatic
        fun newInstance(isUseful: Boolean) =
            RecyclerFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(IS_USEFUL, isUseful)
                }
            }

        const val IS_USEFUL = "isUseful"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            activity = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recycleFragmentViewModel = activity.let {
            ViewModelProvider(it).get(
                RecycleFragmentViewModel::class.java
            )
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
        adapter = HabitAdapter(result)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        float_button.setOnClickListener {
            val habitFragment = HabitFragment()
            activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, habitFragment)
                .addToBackStack(null)
                .commit()
        }

        adapter.setListener(object : Listener {
            override fun onClick(position: Int) {
                val habit: Habit? = result[position]
                val habitFragment = HabitFragment.newInstance(habit, position)
                habitFragment.let {
                    activity.supportFragmentManager
                        .beginTransaction()
                        .add(R.id.container, it)
                        .addToBackStack(null)
                        .commit()
                }
            }
        })
        if (arguments != null) {
            if (!requireArguments().getBoolean(IS_USEFUL)) {
                val data: LiveData<List<Habit>> = recycleFragmentViewModel.getHarmful()
                data.observe(viewLifecycleOwner,
                    { list ->
                        result = list as ArrayList<Habit>
                        adapter.setData(result)
                        adapter.notifyDataSetChanged()
                    })
            } else {
                val data: LiveData<List<Habit>> = recycleFragmentViewModel.getUseful()
                data.observe(viewLifecycleOwner,
                    { list ->
                        result = list as ArrayList<Habit>
                        adapter.setData(result)
                        adapter.notifyDataSetChanged()
                    })
            }
        }
    }
}
