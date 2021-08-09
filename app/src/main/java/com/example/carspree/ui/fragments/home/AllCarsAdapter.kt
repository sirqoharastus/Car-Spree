package com.example.carspree.ui.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carspree.R
import com.example.carspree.databinding.AllCarsItemLayoutBinding
import com.example.carspree.models.Result
import java.math.RoundingMode

class AllCarsAdapter: RecyclerView.Adapter<AllCarsAdapter.AllCarsViewHolder>() {
    //INITIALIZING THE RECYCLERVIEW LIST AND CONTROLLER
    private var allCarsList = ArrayList<Result>()
    private var controller: NavController? = null
    //HOLDS THE VIEWS BEFORE BINDING
    inner class AllCarsViewHolder(var binding: AllCarsItemLayoutBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(car: Result){
            binding.carModelNameTextView.text = car.title
            binding.carMakeTextView.text = car.state
            binding.carPriceTextView.text = "N${car.marketplacePrice}"
            binding.starRatingTextView.text = "(${car.gradeScore.toInt().toBigDecimal().setScale(1, RoundingMode.UP)})"
            Glide.with(binding.cardImageImageView)
                .load(car.imageUrl)
                .into(binding.cardImageImageView)
        }
    }
    //SETTER FOR THE RECYCLERVIEW ADAPTER LIST
    fun getAllCarsList(cars: ArrayList<Result>){
        this.allCarsList = cars
    }
    //THE RECYCLERVIEW ITEM IS INFLATED
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllCarsViewHolder {
        val binding = AllCarsItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllCarsViewHolder(binding)
    }
    //BINDS THE VIEWS TO THE DATA FROM THE RECYCLERVIEW LIST
    override fun onBindViewHolder(holder: AllCarsViewHolder, position: Int) {
        holder.bind(allCarsList[position])
        holder.itemView.setOnClickListener {
            controller = Navigation.findNavController(holder.itemView)
            val action = HomeFragmentDirections.actionHomeFragmentToCarDetailsFragment(allCarsList[position].id)
                controller!!.navigate(action)
        }
    }
    //RETURNS THE RECYCLERVIEW LIST SIZE
    override fun getItemCount() = allCarsList.size
}