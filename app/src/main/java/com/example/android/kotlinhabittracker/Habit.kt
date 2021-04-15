package com.example.android.kotlinhabittracker

import android.os.Parcelable
import android.text.Editable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

/**
 * Created by Anton on 15.03.2021.
 */
@Parcelize
data class Habit(
    var name: String, var description: String, var priority: String, var type: String,
    var numberOfRuns: String, var frequencyOfExecution: String): Parcelable
{

}