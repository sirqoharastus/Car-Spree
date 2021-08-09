package com.example.carspree.network

import com.example.carspree.models.CarDetails
import com.example.carspree.models.CarMakes
import com.example.carspree.models.CarMedia
import com.example.carspree.models.Cars
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("make?popular=true")
    suspend fun getAllCarMakes(): CarMakes

    @GET("car/search")
    suspend fun getAllCars(): Cars

    @GET("car/{carId}")
    suspend fun getCarDetails(@Path("carId") carId: String): CarDetails

    @GET("car_media")
    suspend fun getCarMedia(@Query("carId") carId: String): CarMedia
}