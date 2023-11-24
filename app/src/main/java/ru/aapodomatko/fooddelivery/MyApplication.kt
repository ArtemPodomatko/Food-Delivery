package ru.aapodomatko.fooddelivery

import android.app.Application
import ru.aapodomatko.fooddelivery.database.AppRoomDatabase
import ru.aapodomatko.fooddelivery.database.repository.RoomRepository

class MyApplication : Application() {
    companion object {
        lateinit var INSTANCE : MyApplication
            private set
    }

    lateinit var roomRepository: RoomRepository

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        val dao = AppRoomDatabase.getInstance(context = this).getFoodRoomDao()
        roomRepository = RoomRepository(dao)
    }
}