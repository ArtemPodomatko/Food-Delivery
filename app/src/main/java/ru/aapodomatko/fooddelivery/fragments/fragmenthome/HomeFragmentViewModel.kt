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
import ru.aapodomatko.fooddelivery.MyApplication
import ru.aapodomatko.fooddelivery.database.AppRoomDatabase
import ru.aapodomatko.fooddelivery.database.repository.RoomRepository
import ru.aapodomatko.fooddelivery.models.PopularFoodModel


@Suppress("UNCHECKED_CAST")
class HomeFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val context = application
    private val repository = (application as MyApplication).roomRepository

    val foodItemsLiveData: MutableLiveData<List<PopularFoodModel>> = MutableLiveData()

    val selectedFoodItem: MutableLiveData<PopularFoodModel> = MutableLiveData()
    init {
        getFoodItems()
        Log.d("MyLog", "init database")
    }


    private fun getFoodItems() {
        viewModelScope.launch {
            val foodItems = repository.takeAllItems()
            if (foodItems.isNotEmpty()) {
                foodItemsLiveData.postValue(foodItems)
            } else {
                Log.d("MyLog", "Can't add items")
            }
        }
    }

    fun addFoodItem(foodItem: PopularFoodModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val count = repository.getItemsCount()
            if (count < 6) repository.createFoodItem(foodItem)
            else Log.d("MyLog", "Cannot add more then 3 items")

        }
    }

    fun deleteFoodItem(foodItem: PopularFoodModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val items = repository.takeAllItems()
            items.forEach{
                repository.deleteFoodItem(it)
            }
        }
    }

    fun selectedItem(foodItem: PopularFoodModel) {
        selectedFoodItem.postValue(foodItem)
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

