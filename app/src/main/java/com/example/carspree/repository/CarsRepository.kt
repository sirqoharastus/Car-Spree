package com.example.carspree.repository

import com.example.carspree.network.retrofitService

class CarsRepository {
    suspend fun getAllCarMakes() = retrofitService.getAllCarMakes()

    suspend fun getAllCars() = retrofitService.getAllCars()

    suspend fun getCarDetails(carId: String) = retrofitService.getCarDetails(carId)

    suspend fun getCarMedia(carId: String) = retrofitService.getCarMedia(carId)
}