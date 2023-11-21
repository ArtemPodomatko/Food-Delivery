package ru.aapodomatko.fooddelivery.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.aapodomatko.fooddelivery.utils.Constants.FOOD_DATABASE

@Entity(tableName = FOOD_DATABASE)
data class PopularFoodModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val foodImage: Int? = null,
    val foodName: String = "",
    val foodPrice: String = ""
)
