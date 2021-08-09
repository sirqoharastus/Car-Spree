package com.example.carspree.ui.fragments.carDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.carspree.R
import com.example.carspree.databinding.FragmentCarDetailsBinding
import com.example.carspree.ui.fragments.home.HomeFragment


class CarDetailsFragment : Fragment() {
    //DECLARING AND INITIALIZING VARIABLES
    private val navArgs by navArgs<CarDetailsFragmentArgs>()
    private var _binding: FragmentCarDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CardDetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // INFLATING THE LAYOUT FOR THIS FRAGMENT
        _binding = FragmentCarDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //GETTING CAR ID PASSED THROUGH SAFE ARGS FROM HOME FRAGMENT
        val carId = navArgs.carId
        //MAKING CALL TO GET CAR DETAILS
        viewModel.getCarDetails(carId)
        //OBSERVING THE RESULT FROM THE CALL MADE AND UPDATING THE UI
        viewModel.carDetails.observe(viewLifecycleOwner, {
            binding.carAddressTextView.text = it.address
            binding.carModelTextView.text = it.model.make.name
            binding.carNameTextView.text = it.model.name
            binding.carPriceTextView.text = it.marketplacePrice.toString()
            binding.carYearTextView.text = it.year.toString()
            context?.let { it1 ->
                Glide.with(it1)
                    .load(it.imageUrl)
                    .into(binding.carImageImageView)
            }
        })

        binding.seeCarMediaButton.setOnClickListener {
            val action = CarDetailsFragmentDirections.actionCarDetailsFragmentToCarMediaFragment(carId)
            findNavController().navigate(action)
        }
    }
    //SETTING BINDING TO NULL TO AVOID MEMORY LEAKS
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}