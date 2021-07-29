package com.example.android.kotlinhabittracker

import android.os.Parcelable
import android.text.Editable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

/**
 * Created by Anton on 15.03.2021.
 */
@Parcelize
@Entity
data class Habit(@PrimaryKey(autoGenerate = true) var id: Long = 0, var name: String, var description: String, var priority: String, var type: String, var numberOfRuns: String,
                 var frequencyOfExecution: String): Parcelable{
}