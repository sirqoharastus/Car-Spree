package com.example.carspree.ui.fragments.carMedia

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carspree.databinding.FragmentCarMediaBinding
import com.example.carspree.models.CarMediaX

class CarMediaFragment : Fragment() {
    //DECLARING AND INITIALIZING VARIABLES
    private val navArgs by navArgs<CarMediaFragmentArgs>()
    private val viewModel: CarMediaViewModel by viewModels()
    lateinit var mediaAdapter: CarMediaPicturesAdapter
    private var _binding: FragmentCarMediaBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // INFLATING THE LAYOUT OF THIS FRAGMENT
        _binding = FragmentCarMediaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //GETTING CAR ID  VARIABLE PASSED VIA NAV ARGS
        val carId = navArgs.carId
        //SETTING UP RECYCLERVIEW
        val recyclerview = binding.carMediaRecyclerView
         mediaAdapter = CarMediaPicturesAdapter()
        recyclerview.apply {
            adapter = mediaAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        }
        //MAKING THE CALL TO GET CAR MEDIA
        viewModel.getCarMedia(carId)
        //OBSERVING THE RESULT AND SENDING THE LIST TO THE RECYCLERVIEW ADAPTER
        viewModel.carMediaLiveData.observe(viewLifecycleOwner, {
            var carMediaPictures = ArrayList<CarMediaX>()
            var carMediaVideos = ArrayList<CarMediaX>()
            for(i in it.carMediaList){
                if (i.type == "image/jpeg"){
                    carMediaPictures.add(i)
                    Log.d("pixar", i.toString())
                }
                else{
                    carMediaVideos.add(i)
                }
            }
            mediaAdapter.getPicturesList(carMediaPictures)
            Log.d("pic", carMediaPictures[0].url)
            mediaAdapter.notifyDataSetChanged()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}