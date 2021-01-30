package com.fghilmany.kuliahyuk.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fghilmany.kuliahyuk.core.data.DataRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailViewModel(private val dataRepository: DataRepository) : ViewModel(){

    private var id = 0

    fun setId(id: Int){
        this.id = id
    }

    fun getCriteria() = dataRepository.getCriteria().asLiveData()

    fun gerResult() = dataRepository.getResult().asLiveData()

    fun getAlternativeValueById() = dataRepository.getAlternativeValueById(id).asLiveData()

    fun deleteAlternativeValue() = GlobalScope.launch {
        dataRepository.deleteAlternativeById(id)
    }

    fun deleteResult() = GlobalScope.launch {
        dataRepository.deleteResultById(id)
    }

    fun deletePreferenceValue() = GlobalScope.launch {
        dataRepository.deletePreferenceValueById(id)
    }

}