package com.fghilmany.kuliahyuk.ui.criteria

import android.location.Criteria
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fghilmany.kuliahyuk.core.data.DataRepository
import com.fghilmany.kuliahyuk.core.data.source.local.entity.CriteriaEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CriteriaViewModel (private val dataRepository: DataRepository): ViewModel(){

    private var id : Int = 0

    fun setParameter(id: Int){
        this.id = id
    }

    fun insertCriteria(criteria: CriteriaEntity) = GlobalScope.launch {
        dataRepository.insertCriteria(criteria)
    }

    fun getListCriteria()= dataRepository.getCriteria().asLiveData()

    fun deleteCriteriaById() = GlobalScope.launch {
        dataRepository.deleteCriteriaById(id)
    }

}