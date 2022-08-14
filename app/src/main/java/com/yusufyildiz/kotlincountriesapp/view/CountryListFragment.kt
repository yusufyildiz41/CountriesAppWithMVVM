package com.yusufyildiz.kotlincountriesapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusufyildiz.kotlincountriesapp.adapter.CountryAdapter
import com.yusufyildiz.kotlincountriesapp.databinding.FragmentCountryListBinding
import com.yusufyildiz.kotlincountriesapp.viewmodel.CountryListViewModel


class CountryListFragment : Fragment() {

    private lateinit var viewModel : CountryListViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())


    private lateinit var binding : FragmentCountryListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCountryListBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CountryListViewModel::class.java) // view model initialize

        viewModel.refreshData()

        binding.countryList.layoutManager = LinearLayoutManager(context)
        binding.countryList.adapter = countryAdapter

        /*
        binding.toCountrieDetailButton.setOnClickListener { view ->

            findNavController().navigate(CountryListFragmentDirections.actionCountryListFragmentToCountryDetailFragment())

        }

         */

        binding.swipeRefreshLayout.setOnRefreshListener {

            binding.countryList.visibility = View.GONE
            binding.countryError.visibility = View.GONE
            binding.countryLoading.visibility = View.VISIBLE
            viewModel.refreshFromAPI()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()



    }

    fun observeLiveData()
    {
        viewModel.countries.observe(viewLifecycleOwner, Observer { countries ->

            countries?.let {
                binding.countryList.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }

        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer{ error ->

            error?.let {
                if(it)
                {
                    binding.countryError.visibility = View.VISIBLE
                }
                else
                {
                    binding.countryError.visibility = View.GONE
                }
            }

        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading ->

            loading?.let {
                if(it)
                {
                    binding.countryLoading.visibility = View.VISIBLE
                    binding.countryList.visibility = View.GONE
                    binding.countryError.visibility = View.GONE
                }
                else
                {
                    binding.countryLoading.visibility = View.GONE
                }
            }

        })


    }

}