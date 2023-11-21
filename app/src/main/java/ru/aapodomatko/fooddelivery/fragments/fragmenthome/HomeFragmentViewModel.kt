package ru.aapodomatko.fooddelivery.fragments.fragmenthome

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.aapodomatko.fooddelivery.database.AppRoomDatabase
import ru.aapodomatko.fooddelivery.database.repository.RoomRepository
import ru.aapodomatko.fooddelivery.models.PopularFoodModel
import ru.aapodomatko.fooddelivery.utils.REPOSITORY

@Suppress("UNCHECKED_CAST")
class HomeFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val context = application

    val foodItemsLiveData: MutableLiveData<List<PopularFoodModel>> = MutableLiveData()

    init {
        initDatabase()
        getFoodItems()
        Log.d("MyLog", "init database")
    }
    fun initDatabase() {
        val dao = AppRoomDatabase.getInstance(context = context).getFoodRoomDao()
        REPOSITORY = RoomRepository(dao)
    }

    private fun getFoodItems() {
        viewModelScope.launch {
            val foodItems = REPOSITORY.takeAllItems()
            if (foodItems.isNotEmpty()) {
                foodItemsLiveData.postValue(foodItems)
            } else {
                Log.d("MyLog", "Can't add items")
            }
        }
    }

    fun addFoodItem(foodItem: PopularFoodModel) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.createFoodItem(foodItem)

        }
    }


    class HomeFragmentViewModelFactory(private val application: Application)
        : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeFragmentViewModel::class.java)) {
                return HomeFragmentViewModel(application = application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
        }
}

