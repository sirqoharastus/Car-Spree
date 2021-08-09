package com.example.carspree.ui.fragments.carMedia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carspree.models.CarMedia
import com.example.carspree.repository.CarsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CarMediaViewModel: ViewModel() {
    private val repository = CarsRepository()
    private var _carMediaLiveData = MutableLiveData<CarMedia>()
    val carMediaLiveData: LiveData<CarMedia> get() = _carMediaLiveData

    fun getCarMedia(carId: String){
        CoroutineScope(Dispatchers.IO).launch {
            val result = repository.getCarMedia(carId)
            withContext(Dispatchers.Main){
                _carMediaLiveData.value = result
            }
        }
    }
}