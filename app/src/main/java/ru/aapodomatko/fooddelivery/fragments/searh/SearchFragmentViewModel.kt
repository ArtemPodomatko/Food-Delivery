package ru.aapodomatko.fooddelivery.fragments.searh

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.aapodomatko.fooddelivery.MyApplication
import ru.aapodomatko.fooddelivery.models.PopularFoodModel


class SearchFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val context = application
    private val repository = (application as MyApplication).roomRepository
    val foodItemsLiveData = MutableLiveData<List<PopularFoodModel>>()


    private val _searchResults = MutableLiveData<List<PopularFoodModel>>()
    val searchResults: LiveData<List<PopularFoodModel>> = _searchResults

    init {
        getFoodItems()
    }

    fun getFoodItems() {
        viewModelScope.launch(Dispatchers.IO) {
            val items = repository.takeAllItems()
            foodItemsLiveData.postValue(items)
        }
    }

    fun searchItems(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val results = repository.searchItems(query)
            _searchResults.postValue(results)
        }
    }

    class SearchFragmentViewModelFactory(private val application: Application)
        : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SearchFragmentViewModel::class.java)) {
                return SearchFragmentViewModel(application = application) as T
            }
            throw IllegalStateException("Unknown ViewModel Class")
        }
    }
}