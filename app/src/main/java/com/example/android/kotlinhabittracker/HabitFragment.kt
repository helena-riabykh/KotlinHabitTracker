package com.example.android.kotlinhabittracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_habit.*

private const val HABIT_EXTRA = "habit"
private const val POSIT_EXTRA = "0"

class HabitFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(habit: Habit?, posit: Int): HabitFragment {
            val fragment = HabitFragment()
            val bundle = Bundle()
            bundle.putInt(POSIT_EXTRA, posit)
            bundle.putParcelable(HABIT_EXTRA, habit)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_habit, container, false)
        val button = view.findViewById<Button>(R.id.button_ready)

        button.setOnClickListener {
            val name = editName.text.toString()
            val description = editDescription.text.toString()
            val priority = spinner.selectedItem.toString()
            val type =
                if (R.id.radio_useful == radioGroup.checkedRadioButtonId) "Useful" else "Harmful"
            val numberOfRuns = numberOfRuns.text.toString()
            val frequencyOfExecution = frequencyOfExecution.text.toString()
            val habit =
                Habit(
                    name, description, priority, type,
                    numberOfRuns, frequencyOfExecution
                )
            val recyclerFragment = RecyclerFragment.newInstance(habit)
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, recyclerFragment)
                .commit()
        }
        return view
    }
}
