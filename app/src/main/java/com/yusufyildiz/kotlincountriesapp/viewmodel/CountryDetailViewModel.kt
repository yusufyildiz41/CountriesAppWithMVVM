package com.yusufyildiz.kotlincountriesapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusufyildiz.kotlincountriesapp.database.CountryDatabase
import com.yusufyildiz.kotlincountriesapp.model.Country
import kotlinx.coroutines.launch

class CountryDetailViewModel(application: Application) : BaseViewModel(application) {

    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom(uuid : Int)
    {
        launch{
            val dao = CountryDatabase(getApplication()).countryDao()
            val country = dao.getCountry(uuid)
            countryLiveData.value = country

        }
    }


}