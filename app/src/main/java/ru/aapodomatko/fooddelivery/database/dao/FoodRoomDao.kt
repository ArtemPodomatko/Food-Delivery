package ru.aapodomatko.fooddelivery.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.aapodomatko.fooddelivery.models.PopularFoodModel

@Dao
interface FoodRoomDao {

    @Query("SELECT * FROM popular_food")
    fun getAllNotes(): List<PopularFoodModel>

    @Insert
    fun addFoodItem(foodItem: PopularFoodModel)

    @Query("SELECT COUNT(*) FROM popular_food")
    fun getCount(): Int

    @Delete
    fun deleteFoodItem(foodItem: PopularFoodModel)

    @Query("SELECT * FROM popular_food WHERE foodName LIKE :query || '%'")
    fun searchItems(query: String): List<PopularFoodModel>

}