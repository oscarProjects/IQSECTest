package com.example.iqsectest.ui.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.iqsectest.R
import com.example.iqsectest.data.model.Estado
import com.example.iqsectest.data.model.Pais
import com.example.iqsectest.databinding.FragmentMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapsFragment : Fragment(), GoogleMap.OnMarkerClickListener {

    private lateinit var binding: FragmentMapsBinding

    private lateinit var mapsViewModel: MapsViewModel

    lateinit var mapFragment : SupportMapFragment

    lateinit var paisArg: Pais

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(arguments != null){
            paisArg = requireArguments().getSerializable("pais") as Pais
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMapsBinding.inflate(layoutInflater)
        mapsViewModel = ViewModelProvider(this)[MapsViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        initObservers()
        loadData()
    }

    private fun initObservers() {
        mapsViewModel.statesList.observe(viewLifecycleOwner) {
            if(!it.isNullOrEmpty()){
                loadMarkers(it)
            }
        }
    }

    private fun loadData(){
        mapsViewModel.getStates(paisArg.idPais)
    }

    private fun loadMarkers(states: List<Estado>) {
        mapFragment.getMapAsync { googleMap ->

            googleMap.uiSettings.isZoomControlsEnabled = true

            for (state in states) {
                val latLongPair = state.coordenadas?.let { mapsViewModel.parseLatLongFromString(it) }
                val loc = latLongPair?.let {
                    LatLng(
                        it.first,
                        latLongPair.second
                    )
                }
                loc?.let { MarkerOptions().position(it).title(state.estadoNombre) }?.let { googleMap.addMarker(it) }
                loc?.let { CameraUpdateFactory.newLatLng(it) }?.let { googleMap.moveCamera(it) }
            }

            googleMap.setOnMarkerClickListener(this)
        }
    }

    override fun onMarkerClick(p0: Marker): Boolean {
        Toast.makeText(requireContext(), p0.title, Toast.LENGTH_SHORT).show()
        return true
    }

}