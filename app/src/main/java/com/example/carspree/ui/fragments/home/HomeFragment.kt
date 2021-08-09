package com.example.carspree.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carspree.R
import com.example.carspree.databinding.FragmentHomeBinding
import com.example.carspree.models.Cars
import com.example.carspree.models.Make
import com.example.carspree.models.Result

class HomeFragment : Fragment() {
    //DECLARATION AND INITIALIZATION OF VARIABLES
    private var _binding: FragmentHomeBinding? = null
    val binding get() = _binding!!
    lateinit var carMakeAdapter: CarMakeAdapter
    lateinit var allCarsAdapter: AllCarsAdapter
    private val viewModel by viewModels<HomeFragmentViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // LAYOUT FOR THIS FRAGMENT IS INFLATED
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //INITIALIZING RECYCLERVIEW ADAPTERS
        carMakeAdapter = CarMakeAdapter()
        allCarsAdapter = AllCarsAdapter()
        //SETTING UP RECYCLERVIEW
        val recyclerView = binding.carMakeRecyclerview
        val allCarsRecyclerView = binding.allCarsRecyclerView
        recyclerView.apply {
            adapter = carMakeAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        allCarsRecyclerView.apply {
            adapter = allCarsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        //MAKING THE CALL TO GET DATA FROM THE API
        viewModel.getAllCarMaker()
        viewModel.getAllCars()
        //OBSERVING THE RESULT FROM THE CALL AND UPDATING THE RECYCLERVIEW LIST
        viewModel.carMakesLiveData.observe(viewLifecycleOwner, {
            carMakeAdapter.getCarsMakeList(it as ArrayList<Make>)
            carMakeAdapter.notifyDataSetChanged()
        })
        //OBSERVING THE RESULT FROM THE CALL AND UPDATING THE RECYCLERVIEW LIST
        viewModel.allCars.observe(viewLifecycleOwner, {
            allCarsAdapter.getAllCarsList(it as ArrayList<Result>)
            allCarsAdapter.notifyDataSetChanged()
        })
        binding.run {
            recyclerView.apply {
                adapter = carMakeAdapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }
            allCarsRecyclerView.apply {
                adapter = allCarsAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
            //MAKING THE CALL TO GET DATA FROM THE API
            viewModel.getAllCarMaker()
            viewModel.getAllCars()
            //OBSERVING THE RESULT FROM THE CALL AND UPDATING THE RECYCLERVIEW LIST
            viewModel.carMakesLiveData.observe(viewLifecycleOwner, {
                carMakeAdapter.getCarsMakeList(it as ArrayList<Make>)
            })
            //OBSERVING THE RESULT FROM THE CALL AND UPDATING THE RECYCLERVIEW LIST
            viewModel.allCars.observe(viewLifecycleOwner, {
                allCarsAdapter.getAllCarsList(it as ArrayList<Result>)
            })
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.allCars.observe(viewLifecycleOwner, {
                    var filteredList = ArrayList<Result>()
                    for (car in it){
                        if (car.title.contains(query.toString().capitalize())){
                            filteredList.add(car)
                        }
                    }
                    allCarsAdapter.getAllCarsList(filteredList)
                })
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.allCars.observe(viewLifecycleOwner, {
                    var filteredList = ArrayList<Result>()
                    for (car in it){
                        if (car.title.contains(newText.toString().capitalize())){
                            filteredList.add(car)
                        }
                    }
                    allCarsAdapter.getAllCarsList(filteredList)
                })
                return true
            }

        })
    }
    //SETTING BINDING TO NULL TO AVOID MEMORY LEAKS
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}