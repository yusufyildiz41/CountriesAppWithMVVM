package com.yusufyildiz.kotlincountriesapp.service

import com.yusufyildiz.kotlincountriesapp.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountryAPI {

    //GET,POST

    //https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

    //BASE_URL -> https://raw.githubusercontent.com/
    //EXT -> atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json


    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getAllCountries() : Single<List<Country>> // single, observable, completable ... -> Rxjava call method

}
