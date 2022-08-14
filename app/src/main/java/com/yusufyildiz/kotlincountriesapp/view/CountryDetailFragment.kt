package com.yusufyildiz.kotlincountriesapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.yusufyildiz.kotlincountriesapp.R
import com.yusufyildiz.kotlincountriesapp.databinding.FragmentCountryDetailBinding
import com.yusufyildiz.kotlincountriesapp.util.downloadFromURL
import com.yusufyildiz.kotlincountriesapp.util.placeHolderProgressBar
import com.yusufyildiz.kotlincountriesapp.viewmodel.CountryDetailViewModel


class CountryDetailFragment : Fragment() {


    private lateinit var viewModel : CountryDetailViewModel
    private var countryUuid = 0
    private lateinit var dataBinding : FragmentCountryDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_country_detail,container,false)

        // Inflate the layout for this fragment
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        binding.goCountryListButton.setOnClickListener {

            findNavController().navigate(CountryDetailFragmentDirections.actionCountryDetailFragmentToCountryListFragment())

        }

         */

        arguments?.let {
            countryUuid = CountryDetailFragmentArgs.fromBundle(it).countryUuid  // uuid will come from CountryListFragment
        }

        viewModel = ViewModelProviders.of(this).get(CountryDetailViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)

        observeLiveData()


    }

    private fun observeLiveData()
    {
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->

            country?.let {

                dataBinding.selectedCountry = country
                /*
                binding.countryDetailNameText.text = country.countryName
                binding.countryDetailCapitalText.text = country.countryCapital
                binding.countryDetailCurrencyText.text = country.countryCurrency
                binding.countryDetailRegionText.text = country.countryRegion
                binding.countryDetailLanguage.text = country.countryLanguage

                context?.let {
                    binding.countryDetailImage.downloadFromURL(country.imageUrl, placeHolderProgressBar(it))
                }

                 */


            }

        })
    }

}