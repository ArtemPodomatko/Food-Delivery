package ru.aapodomatko.fooddelivery.fragments.menu

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.aapodomatko.fooddelivery.MyApplication
import ru.aapodomatko.fooddelivery.models.PopularFoodModel

class MenuFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val context = application
    val repository = (application as MyApplication).roomRepository

    val foodItemsLiveData: MutableLiveData<List<PopularFoodModel>> = MutableLiveData()
    val selectedFoodItem: MutableLiveData<PopularFoodModel> = MutableLiveData()

    init {
        getFoodItems()
    }

    fun getFoodItems() {
        viewModelScope.launch(Dispatchers.IO) {
            val foodItems = repository.takeAllItems()
            foodItemsLiveData.postValue(foodItems)
        }
    }

    fun selectedItem(foodItem: PopularFoodModel) {
        selectedFoodItem.postValue(foodItem)
    }


    class MenuFragmentViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MenuFragmentViewModel::class.java)) {
                return MenuFragmentViewModel(application = application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}