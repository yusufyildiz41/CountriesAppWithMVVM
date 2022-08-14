package com.yusufyildiz.kotlincountriesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.yusufyildiz.kotlincountriesapp.R
import com.yusufyildiz.kotlincountriesapp.databinding.ItemCountryRowBinding
import com.yusufyildiz.kotlincountriesapp.model.Country
import com.yusufyildiz.kotlincountriesapp.util.downloadFromURL
import com.yusufyildiz.kotlincountriesapp.util.placeHolderProgressBar
import com.yusufyildiz.kotlincountriesapp.view.CountryListFragmentDirections
import kotlinx.android.synthetic.main.item_country_row.view.*

class CountryAdapter(val countryList : ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(),CountryClickListener{

    class CountryViewHolder(val view : ItemCountryRowBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.item_country_row,parent,false)
        val view = DataBindingUtil.inflate<ItemCountryRowBinding>(inflater,R.layout.item_country_row,parent,false)
        return CountryViewHolder(view)

    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {


        holder.view.country = countryList[position]
        holder.view.listener = this

        /*
        holder.view.countryNameText.text = countryList[position].countryName
        holder.view.countryRegionText.text = countryList[position].countryRegion
        holder.view.imageView.downloadFromURL(countryList[position].imageUrl, placeHolderProgressBar(holder.view.context))

        holder.view.setOnClickListener {
            val action = CountryListFragmentDirections.actionCountryListFragmentToCountryDetailFragment(countryList[position].uuid)

            Navigation.findNavController(it).navigate(action)

        }

         */

    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun updateCountryList(newCountryList : List<Country>)
    {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClicked(v: View) {
        val uuid = v.countryUuidText.text.toString().toInt()
        val action = CountryListFragmentDirections.actionCountryListFragmentToCountryDetailFragment(uuid)
        Navigation.findNavController(v).navigate(action)

    }

}