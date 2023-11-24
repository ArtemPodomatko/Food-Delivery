package ru.aapodomatko.fooddelivery.database.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.aapodomatko.fooddelivery.database.dao.FoodRoomDao
import ru.aapodomatko.fooddelivery.models.PopularFoodModel

class RoomRepository(private val foodRoomDao: FoodRoomDao) {
    suspend fun takeAllItems() = withContext(Dispatchers.IO) {
        foodRoomDao.getAllNotes()
    }


    fun createFoodItem(foodItem: PopularFoodModel) {
        foodRoomDao.addFoodItem(foodItem = foodItem)
    }

    fun getItemsCount() : Int {
        return foodRoomDao.getCount()
    }

    fun deleteFoodItem(foodItem: PopularFoodModel) {
        foodRoomDao.deleteFoodItem(foodItem)
    }

    fun searchItems(query: String): List<PopularFoodModel> {
        return foodRoomDao.searchItems(query)
    }
}