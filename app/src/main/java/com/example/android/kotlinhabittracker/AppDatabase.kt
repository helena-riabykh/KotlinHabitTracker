package com.example.android.kotlinhabittracker

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by Anton on 22.07.2021.
 */
@Database(entities = [Habit::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getHabitDao(): HabitDao
}

