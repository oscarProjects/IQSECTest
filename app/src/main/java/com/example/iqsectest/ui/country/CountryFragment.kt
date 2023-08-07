package com.example.iqsectest.ui.country

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iqsectest.R
import com.example.iqsectest.adapters.CountriesAdapter
import com.example.iqsectest.data.model.Pais
import com.example.iqsectest.databinding.FragmentCountryBinding
import com.example.iqsectest.di.manager.NavigationManager
import com.example.iqsectest.listener.ClickListener
import com.example.iqsectest.util.ConstantsIQSEC
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CountryFragment : Fragment(), ClickListener{

    private lateinit var _binding: FragmentCountryBinding

    private lateinit var countryViewModel: CountryViewModel

    private var adapter: CountriesAdapter? = null

    @Inject
    lateinit var navigation: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            /*param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)*/
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountryBinding.inflate(inflater, container, false)

        countryViewModel = ViewModelProvider(this)[CountryViewModel::class.java]

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        countryViewModel.getCountries()
    }

    private fun initObservers() {
        countryViewModel.countriesList.observe(viewLifecycleOwner) {
            if(!it.isNullOrEmpty()){
                _binding.recyclerCountries.visibility = View.VISIBLE
                adapter = CountriesAdapter(it, this)
                _binding.recyclerCountries.layoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
                _binding.recyclerCountries.setHasFixedSize(true)
                _binding.recyclerCountries.adapter = adapter
            }
        }
    }

    override fun onClickCard(pais: Pais?) {
        if (pais != null) {
            Toast.makeText(requireContext(), "Hola: ${pais.nombrePais}", Toast.LENGTH_SHORT).show()
            navigation.onNavigate(view, R.id.action_detailCountry, buildArguments(pais))
        }
    }

    private fun buildArguments(pais: Pais): Bundle{
        val bundle = Bundle()
        bundle.putSerializable(ConstantsIQSEC.ARG_PAIS, pais)
        return bundle
    }
}