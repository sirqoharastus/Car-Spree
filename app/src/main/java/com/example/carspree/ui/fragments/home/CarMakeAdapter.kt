package com.example.carspree.ui.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carspree.R
import com.example.carspree.databinding.CarMakesItemLayoutBinding
import com.example.carspree.models.Make

class CarMakeAdapter: RecyclerView.Adapter<CarMakeAdapter.CarMakeViewHolder>() {
    //RECYCLERVIEW LIST FOR THE ADAPTER
    private var carsMakeList = ArrayList<Make>()

    //HOLDS THE VIEWS BEFORE BINDING
    inner class CarMakeViewHolder(var binding: CarMakesItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(carsMake: Make){
            binding.carMakeName.text = carsMake.name
            Glide.with(binding.carMakeCircleImageView)
                .load(carsMake.imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.carMakeCircleImageView)
        }
    }
    //SETTER FOR THE RECYCLERVIEW LIST
    fun getCarsMakeList(carsMake: ArrayList<Make>){
        this.carsMakeList = carsMake
    }
    //RECYCLERVIEW ITEM IS INFLATED
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarMakeViewHolder {
        val binding = CarMakesItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarMakeViewHolder(binding)
    }
    //BINDS THE VIEWS TO THE RESPECTIVE DATA FROM THE RECYCLERVIEW LIST
    override fun onBindViewHolder(holder: CarMakeViewHolder, position: Int) {
        holder.bind(carsMakeList[position])

    }
    //RETURNS THE RECYCLERVIEW LIST
    override fun getItemCount() = carsMakeList.size
}