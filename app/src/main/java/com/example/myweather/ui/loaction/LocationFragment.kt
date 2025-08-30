package com.example.myweather.ui.loaction

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.findNavController
import com.example.myweather.databinding.FragmentLoactoinBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationFragment : Fragment() {

    private var _binding: FragmentLoactoinBinding? = null
    private val binding get() = _binding!!
    private val LOCATION_PERMISSION_REQUEST = 1001
    lateinit var locationClient: FusedLocationProviderClient



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding= FragmentLoactoinBinding.inflate(inflater,container,false)
        locationClient= LocationServices.getFusedLocationProviderClient(requireActivity())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            getCurrentLocation()

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null

    }

    companion object {
       const val TAG="LocationFragment"
    }
    private fun LocationFragment.getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST
            )
            return
        }
        locationClient.lastLocation.addOnSuccessListener { location ->
            try {
                if(location!=null){
                    val latitude=location.latitude
                    val longitude=location.longitude
                    Log.d(TAG,"lat $latitude  lon $longitude")
                    val directions= LocationFragmentDirections.actionLocationFragmentToWeatherDataFragment(lat = latitude.toFloat(), lon = longitude.toFloat())
                    findNavController().navigate(directions)
                }
            }catch (e: Exception){
                Log.e(TAG,e.toString())
            }


        }

    }


}

