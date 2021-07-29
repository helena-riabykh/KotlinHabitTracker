package com.example.android.kotlinhabittracker

import android.app.Application
import androidx.room.Room




/**
 * Created by Anton on 22.07.2021.
 */
class App: Application() {
//    private val instance: App? = null
    private var db: AppDatabase? = null

    companion object {
        private var instance: App? = null

        @JvmStatic
        fun getInstance(): App {
            if (instance == null) {
                instance = App()
            }
            return instance as App
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        db = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .allowMainThreadQueries()
            .build()
    }

    fun getDatabase(): AppDatabase? {
        return db
    }
}