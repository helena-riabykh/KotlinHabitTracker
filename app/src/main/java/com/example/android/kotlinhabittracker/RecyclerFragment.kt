package com.example.android.kotlinhabittracker

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.kotlinhabittracker.HabitAdapter.Listener
import kotlinx.android.synthetic.main.fragment_recycler.*
import java.util.*

const val IS_USEFUL = "isUseful"

class RecyclerFragment() : Fragment() {
    private var result: ArrayList<Habit> = arrayListOf()
    private lateinit var adapter: HabitAdapter
    private lateinit var recycleFragmentViewModel: RecycleFragmentViewModel
    private var activity: MainActivity? = null
    private var posit = -1

    companion object {
        @JvmStatic
        fun newInstance(isUseful: Boolean) =
            RecyclerFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(IS_USEFUL, isUseful)
                }
            }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            activity = context
            //           activity?.stateOfBottomSheetCollapsed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            result = savedInstanceState.getParcelableArrayList("result")!!
        }
        adapter = HabitAdapter(result)
        recycleFragmentViewModel = ViewModelProvider(requireActivity()).get(
            RecycleFragmentViewModel::class.java
        )
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

        adapter.setListener(object : Listener {
            override fun onClick(position: Int) {
                val habit: Habit? = result[position]
                val habitFragment = HabitFragment.newInstance(habit, position)
                habitFragment.let {
                    activity?.supportFragmentManager
                        ?.beginTransaction()
                        ?.add(R.id.container, it)
                        ?.addToBackStack(null)
                        ?.commit()
                }
                posit = position
            }
        })

        if (arguments != null) {
            if (!requireArguments().getBoolean(IS_USEFUL)) {
                val data: LiveData<List<Habit>> = recycleFragmentViewModel.getHarmful()
                data.observe(viewLifecycleOwner,
                    { list ->
                        result = list as ArrayList<Habit>
                        adapter.setData(result)
                    })
            } else {
                val data: LiveData<List<Habit>> = recycleFragmentViewModel.getUseful()
                data.observe(viewLifecycleOwner,
                    { list ->
                        result = list as ArrayList<Habit>
                        adapter.setData(result)
                    })
            }
        }
    }

    override fun onSaveInstanceState(saveInstanceState: Bundle) {
        super.onSaveInstanceState(saveInstanceState)
        saveInstanceState.putParcelableArrayList("result", result)
    }
}
