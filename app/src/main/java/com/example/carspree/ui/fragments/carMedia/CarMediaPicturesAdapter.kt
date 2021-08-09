package com.example.carspree.ui.fragments.carMedia

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carspree.R
import com.example.carspree.databinding.CarMediaPicturesLayoutResourceBinding
import com.example.carspree.models.CarMediaX

class CarMediaPicturesAdapter: RecyclerView.Adapter<CarMediaPicturesAdapter.CarMediaPicturesViewHolder>() {
    private var carMediaPictureList = ArrayList<CarMediaX>()
    inner class CarMediaPicturesViewHolder(var binding: CarMediaPicturesLayoutResourceBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(carMedia: CarMediaX){
            binding.carMediaNameTextView.text = carMedia.name
            Glide.with(binding.carMediaImageView)
                .load(carMedia.url)
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.carMediaImageView)
        }
    }
    fun getPicturesList(picturesList: ArrayList<CarMediaX>){
        this.carMediaPictureList = picturesList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarMediaPicturesViewHolder {
        var binding = CarMediaPicturesLayoutResourceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarMediaPicturesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarMediaPicturesViewHolder, position: Int) {
        holder.bind(carMediaPictureList[position])
    }

    override fun getItemCount() = carMediaPictureList.size
}