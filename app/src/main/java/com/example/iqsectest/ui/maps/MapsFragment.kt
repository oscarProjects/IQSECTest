package com.example.iqsectest.ui.maps

import android.os.Bundle
import android.util.Log
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
import com.example.iqsectest.ui.detail.DetailBottomSheet
import com.example.iqsectest.util.ConstantsIQSEC
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

    lateinit var newStates: List<Estado>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(arguments != null){
            paisArg = requireArguments().getSerializable(ConstantsIQSEC.ARG_PAIS) as Pais
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
                newStates = it
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
        val fragment = DetailBottomSheet()
        val state = getData(p0.title)
        fragment.arguments = state?.let { it1 -> buildArguments(it1, paisArg) }
        fragment.let { it.show(requireFragmentManager(), it.tag) }
        return true
    }

    private fun buildArguments(estado: Estado, pais: Pais): Bundle{
        val bundle = Bundle()
        bundle.putSerializable(ConstantsIQSEC.ARG_PAIS, pais)
        bundle.putSerializable(ConstantsIQSEC.ARG_ESTADO, estado)
        return bundle
    }

    private fun getData(title: String?): Estado? {
        var stateReturn: Estado? = null
        newStates.forEach{
            if(title.equals(it.estadoNombre)){
                stateReturn = it
            }
        }

        return stateReturn
    }

}