package com.yusufyildiz.kotlincountriesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yusufyildiz.kotlincountriesapp.model.Country

@Database(entities = arrayOf(Country::class), version = 1)

abstract class CountryDatabase : RoomDatabase() {

    abstract fun countryDao() : CountryDAO

    //Singleton Pattern -> Icerisinden tek bir obje olusturulabilen sinif, eger icerisinde obje yok ise olusturuyorduk eger var ise olusturmuyorduk

    // @Volatile -> Bu annotation ile baska threadler tarafindan gorunmesini saglayacak. Coroutineler kullanmasaydik kullanmamiza gerek kalmazdi.


    companion object {  // Her yerden bu objeye ulasmak icin companion object kullanilir

        @Volatile private var instance : CountryDatabase? =null // @Volatile

        private val lock = Any()

        // synchronized -> ayni anda birden fazla thread ulasamaz. Farkli zamanlarda blogun icerisine girebilir. Diger thread'leri senkronize bir sekilde yonetir.
        //invoke -> ateslemek,baslatmak -> instance kontrolu yapiyor ve instance var ise onu dondurmek amacli kullanilir yok ise senkroinize bir sekilde bu instance'a ulasilabilecegini soyluyoruz.
        operator fun invoke(context : Context) = instance ?: synchronized(lock){
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context : Context) = Room.databaseBuilder(
            context.applicationContext,CountryDatabase::class.java,"countrydatabase"
        ).build()

    }

}