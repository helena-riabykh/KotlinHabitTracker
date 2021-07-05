package com.example.android.kotlinhabittracker

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_habit.*

private const val HABIT_EXTRA = "habit"
private const val POSIT_EXTRA = "0"

class HabitFragment : Fragment() {
    private var activity: MainActivity? = null
    private var habitEditable: Habit? = null
    private var positItem = -1
    private var index = -1
    private var changeHabitType = false
    private lateinit var editName: EditText
    private lateinit var editDescription: EditText
    private lateinit var numberOfRuns: EditText
    private lateinit var frequencyOfExecution: EditText
    private lateinit var spinner: Spinner
    private lateinit var radioGroup: RadioGroup

    companion object {
        @JvmStatic
        fun newInstance(habit: Habit?, posit: Int) = HabitFragment().apply {
            arguments = Bundle(2).apply {
                putInt(POSIT_EXTRA, posit)
                putParcelable(HABIT_EXTRA, habit)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            activity = context
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
        editName = view.findViewById(R.id.editName)
        editDescription = view.findViewById(R.id.editDescription)
        numberOfRuns = view.findViewById(R.id.numberOfRuns)
        frequencyOfExecution = view.findViewById(R.id.frequencyOfExecution)
        spinner = view.findViewById(R.id.spinner)
        radioGroup = view.findViewById(R.id.radioGroup)

        arguments?.let {
            positItem = it.getInt(POSIT_EXTRA)
            habitEditable = it.getParcelable(HABIT_EXTRA)
        }
        val model = Model.getInstance()
        index = model.getHabitArrayList().indexOf(habitEditable)
        if (habitEditable != null) {
            editName.setText(habitEditable!!.name)
            editDescription.setText(habitEditable!!.description)
            numberOfRuns.setText(habitEditable!!.numberOfRuns)
            frequencyOfExecution.setText(habitEditable!!.frequencyOfExecution)
            when (habitEditable!!.priority) {
                "paramount" -> spinner.setSelection(0)
                "secondary" -> spinner.setSelection(1)
                else -> {
                    spinner.setSelection(2)
                }
            }

            when (habitEditable!!.type) {
                "Useful" -> radioGroup.check(R.id.radio_useful)
                else -> radioGroup.check(R.id.radio_harmful)
            }
        }

        activity?.stateOfBottomSheetHidden()

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
            if (habitEditable != null) {
                if (habitEditable?.type != habit.type) {
                    changeHabitType = true
                }
            }
            val habitFragmentViewModel = HabitFragmentViewModel()
            habitFragmentViewModel.notify(habit, positItem, changeHabitType, index)
            val inputManager =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
            activity?.stateOfBottomSheetCollapsed()
            activity?.onBackPressed()

        }
        return view
    }
}



