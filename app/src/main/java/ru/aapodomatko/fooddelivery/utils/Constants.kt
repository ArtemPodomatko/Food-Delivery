package ru.aapodomatko.fooddelivery.utils

import ru.aapodomatko.fooddelivery.database.repository.RoomRepository


lateinit var REPOSITORY: RoomRepository
object Constants {
    const val FOOD_DATABASE = "popular_food"
}