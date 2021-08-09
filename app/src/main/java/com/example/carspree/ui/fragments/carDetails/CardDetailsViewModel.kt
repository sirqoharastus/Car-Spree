package com.example.carspree.ui.fragments.carDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carspree.models.CarDetails
import com.example.carspree.repository.CarsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CardDetailsViewModel: ViewModel() {
    //INSTANTIATING REPOSITORY
    private val repository = CarsRepository()
    //INITIALIZING LIVEDATA
    private var _carDetails = MutableLiveData<CarDetails>()
    val carDetails: LiveData<CarDetails> get() = _carDetails
    //FUNCTION TO GET CAR DETAILS
    fun getCarDetails(carId: String){
        viewModelScope.launch(Dispatchers.IO) {
            var carDetails = repository.getCarDetails(carId)
            withContext(Dispatchers.Main){
                _carDetails.value = carDetails
            }
        }
    }
}