package com.example.android.kotlinhabittracker

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass.
 */
private const val STRING_VALUE = "strValue"

class FilterFragment() : Fragment() {
    private var strValue: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            strValue = savedInstanceState.getString(STRING_VALUE).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_filter, container, false)
        val description = view.findViewById<EditText>(R.id.description)
        description.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                val model = Model.getInstance()
                strValue = description.text.toString()
                if (strValue != "") {
                    model.filterName(strValue)
                } else {
                    model.getMutableLiveData()
                }
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
        if (strValue != "") {
            saveInstanceState.putString(STRING_VALUE, strValue)
        }
    }
}

