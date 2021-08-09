package com.example.carspree.ui.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carspree.models.Make
import com.example.carspree.models.Result
import com.example.carspree.repository.CarsRepository
import kotlinx.coroutines.*

class HomeFragmentViewModel(): ViewModel() {
    //INSTANTIATING REPOSITORY
    private val repository: CarsRepository = CarsRepository()
    //LIVEDATA FOR OBSERVING LIST OF CAR MODELS
    private var _carModelsLiveData = MutableLiveData<List<Make>>()
    val carMakesLiveData: LiveData<List<Make>> get() = _carModelsLiveData
    //LIVEDATA FOR OBSERVING LIST OF CARS
    private var _allCars = MutableLiveData<List<Result>>()
    val allCars:LiveData<List<Result>> get() = _allCars

    //FUNCTION TO GET LIST OF ALL CARS AND SET THE RESULT TO THE LIVEDATA VALUE
    fun getAllCarMaker(){
        CoroutineScope(Dispatchers.IO).launch {
            val carMakes = repository.getAllCarMakes()
            withContext(Dispatchers.Main){
                _carModelsLiveData.value = carMakes.makeList
            }
        }
    }

    //FUNCTION TO GET LIST OF ALL CARS AND SET THE RESULT TO THE LIVEDATA VALUE
    fun getAllCars(){
        CoroutineScope(Dispatchers.IO).launch {
            val cars = repository.getAllCars()
            withContext(Dispatchers.Main){
                _allCars.value = cars.result
            }
        }
    }

}