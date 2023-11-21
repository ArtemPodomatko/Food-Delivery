package ru.aapodomatko.fooddelivery.database.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.aapodomatko.fooddelivery.database.dao.FoodRoomDao
import ru.aapodomatko.fooddelivery.models.PopularFoodModel

class RoomRepository(private val foodRoomDao: FoodRoomDao) {
    suspend fun takeAllItems() = withContext(Dispatchers.IO) {
        foodRoomDao.getAllNotes()
    }


    suspend fun createFoodItem(foodItem: PopularFoodModel) {
        foodRoomDao.addFoodItem(foodItem = foodItem)
    }
}