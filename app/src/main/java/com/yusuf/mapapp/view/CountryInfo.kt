package com.yusuf.mapapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yusuf.mapapp.databinding.FragmentCountryInfoBinding
import com.yusuf.mapapp.util.downloadFromUrl
import com.yusuf.mapapp.util.placeHolder
import com.yusuf.mapapp.viewmodel.CountryViewModel


class CountryInfo : Fragment() {

    private var _binding: FragmentCountryInfoBinding? = null
    private val binding get() = _binding!!
    private  var countryId = 0

    lateinit var countryViewModel : CountryViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentCountryInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryId = CountryInfoArgs.fromBundle(it).countryId
        }

        countryViewModel = ViewModelProvider(this)[CountryViewModel::class.java]
        countryViewModel.getDataFromRoom(countryId)



        observeLiveData()

    }


    private fun observeLiveData(){
            countryViewModel.countryLiveModel.observe(viewLifecycleOwner, Observer {
                country -> country?.let {

                binding.countryInfoCapital.text = country.countryCapital
                binding.countryInfoCurrency.text = country.countryCurrency
                binding.countryInfoName.text = country.countryName
                binding.countryInfoLanguage.text = country.countryLanguage
                binding.countryInfoRegion.text = country.countryRegion
                context?.let {
                    binding.countryInfoImage.downloadFromUrl(country.countryFlag, placeHolder(it))

                }
            }
            })
    }
}