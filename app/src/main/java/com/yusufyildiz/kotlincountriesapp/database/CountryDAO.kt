package com.yusufyildiz.kotlincountriesapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yusufyildiz.kotlincountriesapp.model.Country

@Dao
interface CountryDAO {

    //Data Access Object

    @Insert
    suspend fun insertAll(vararg countries : Country) : List<Long>

    // Insert -> INSERT INTO
    // suspend -> coroutine, pause & resume
    // vararg -> multiple country objects
    // List<Long> -> primary keys

    @Query("SELECT * FROM Country") // Entity name = country
    suspend fun getAllCountries(): List<Country>

    @Query("SELECT * FROM Country WHERE uuid = :countryId")
    suspend fun getCountry(countryId : Int) : Country

    @Query("DELETE FROM Country")
    suspend fun deleteAllCountries()


}