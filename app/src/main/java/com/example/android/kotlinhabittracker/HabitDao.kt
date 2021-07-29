package com.example.android.kotlinhabittracker

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Created by Anton on 22.07.2021.
 */
@Dao
interface HabitDao {
    @Query("SELECT * FROM Habit")
    fun getAll(): List<Habit>?

    @Query("SELECT * FROM Habit")
    fun getAllHabit(): LiveData<List<Habit?>?>?

    @Query("DELETE from Habit WHERE id =:id")
    fun deleteById(id: Int)

    @Query("SELECT * FROM Habit WHERE name LIKE :name")
    fun getAllWithNameLike(name: String?): List<Habit>?

    @Insert
    fun insertListHabit(habit: List<Habit?>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(habit: Habit?)

    @Update
    fun update(habit: Habit?)

    @Delete
    fun delete(habit: Habit?)
}