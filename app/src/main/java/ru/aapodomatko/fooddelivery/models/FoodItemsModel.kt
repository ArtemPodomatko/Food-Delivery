package ru.aapodomatko.fooddelivery.models

import ru.aapodomatko.fooddelivery.R

class FoodItemsModel {
    fun createItems(): List<PopularFoodModel> {
        val list = listOf(
            PopularFoodModel(foodName = "Burger", foodPrice = "$5", foodImage = R.drawable.pop_menu_momo),
            PopularFoodModel(foodName = "Sandwich", foodPrice = "$9", foodImage = R.drawable.pop_menu_burger),
            PopularFoodModel(foodName = "Momo", foodPrice = "$5", foodImage = R.drawable.pop_menu_sandwich)

        )
        return list
    }
}