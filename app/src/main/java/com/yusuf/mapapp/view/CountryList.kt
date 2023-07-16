package com.yusuf.mapapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusuf.mapapp.adapter.CountryAdapter
import com.yusuf.mapapp.databinding.FragmentCountryListBinding
import com.yusuf.mapapp.viewmodel.CountryListViewModel


class CountryList : Fragment() {

    private var _binding: FragmentCountryListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : CountryListViewModel
    private val countryAdapter  = CountryAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CountryListViewModel::class.java]
        viewModel.refreshData()

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = countryAdapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.recyclerView.visibility = View.GONE
            binding.textView.visibility= View.GONE
            binding.progressBar.visibility=View.VISIBLE
            viewModel.refreshFromAPI()
            binding.swipeRefreshLayout.isRefreshing = false

        }

        observeLiveData()

    }

    private fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner, Observer {
            countries ->
            countries?.let {
            binding.recyclerView.visibility = View.VISIBLE
            countryAdapter.updateCountry(countries)
        }
        })

       viewModel.error.observe(viewLifecycleOwner, Observer {
           error -> error?.let {
           if (it){
               binding.progressBar.visibility = View.VISIBLE
               binding.recyclerView.visibility = View.INVISIBLE
           }
           else{
               binding.progressBar.visibility = View.GONE
           }
       }

       })


        viewModel.countriesLoading.observe(viewLifecycleOwner, Observer {

            countriesLoading ->countriesLoading?.let {
            if (it){
                binding.textView.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
                binding.recyclerView.visibility = View.GONE

            }
            else{
                binding.textView.visibility= View.GONE
            }
        }


        })
    }



}