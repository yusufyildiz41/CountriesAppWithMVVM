package com.yusufyildiz.kotlincountriesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(application : Application) : AndroidViewModel(application), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main // Oncelikle isini yap sonrasinda Main thread'e don anlamina gelir

    override fun onCleared() {
        super.onCleared()
        job.cancel() // app contexti giderse is iptal edilir
    }


}