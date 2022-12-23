package com.pma.api_demo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pma.api_demo.api.CatWebService
import kotlinx.coroutines.launch

class MainActivityViewModel() : ViewModel() {

    val displayText: MutableLiveData<String> = MutableLiveData()
    private val catWebService by lazy {
        CatWebService()
    }

    fun getCatFact() {
        viewModelScope.launch {
            displayText.value = catWebService.getCatFact().fact
        }
    }

}
