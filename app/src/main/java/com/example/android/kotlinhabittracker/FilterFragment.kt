package com.example.android.kotlinhabittracker

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_filter.*

/**
 * A simple [Fragment] subclass.
 */
class FilterFragment() : Fragment() {
    val habitFragmentViewModel = HabitFragmentViewModel()
    private var strValue: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            strValue = savedInstanceState.getString("strValue")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view = inflater.inflate(R.layout.fragment_filter, container, false)
  //              val description = view!!.findViewById<EditText>(R.id.description)
        description.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                strValue = description.text.toString()
                habitFragmentViewModel.notifyName(strValue)
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                //Прописываем то, что надо выполнить до изменений
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                //только что заменены
            }
        })
        return view
    }

    override fun onSaveInstanceState(saveInstanceState: Bundle) {
        super.onSaveInstanceState(saveInstanceState)
        saveInstanceState.putString("strValue", strValue)
    }

}
