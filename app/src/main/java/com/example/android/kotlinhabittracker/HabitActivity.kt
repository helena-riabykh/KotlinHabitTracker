package com.example.android.kotlinhabittracker

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_habit.*

class HabitActivity : AppCompatActivity() {

    companion object {
        const val HABIT_EXTRA = "habit"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit)
        button_ready.setOnClickListener {
            val name = editName.text.toString()
            val description = editDescription.text.toString()
            val priority = spinner.selectedItem.toString()
            val type = if (R.id.radio_useful == radioGroup.checkedRadioButtonId) "Useful" else "Harmful"
            val numberOfRuns = numberOfRuns.text.toString()
            val frequencyOfExecution = frequencyOfExecution.text.toString()
            val habit =
                Habit(name, description, priority, type,
                    numberOfRuns, frequencyOfExecution)
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(HABIT_EXTRA, habit)
            startActivity(intent)
        }
    }
}

