package ru.aapodomatko.fooddelivery.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.aapodomatko.fooddelivery.database.dao.FoodRoomDao
import ru.aapodomatko.fooddelivery.models.PopularFoodModel
import ru.aapodomatko.fooddelivery.utils.Constants

@Database(entities = [PopularFoodModel::class], version = 1)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun getFoodRoomDao(): FoodRoomDao

    companion object {
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase {
            return if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppRoomDatabase::class.java,
                    Constants.FOOD_DATABASE
                ).build()
                INSTANCE as AppRoomDatabase
            } else INSTANCE as AppRoomDatabase
        }
    }

}