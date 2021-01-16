package com.fghilmany.kuliahyuk.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fghilmany.kuliahyuk.core.data.DataRepository
import com.fghilmany.kuliahyuk.core.data.source.local.entity.ResultEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.pow

class DashboardViewModel (private val dataRepository: DataRepository): ViewModel(){

    private var weight: Double = 0.0
    private var countWeight: Double = 0.0
    private var value: Double = 0.0
    private var scaleWeight: Double = 0.0
    private var preferenceValue: Double = 0.0
    private var totalPreferenceValue: Double = 0.0
    private var state = ""

    fun setState(state : String){
        this.state = state
    }

    fun scaleWeights(weight: Double, countWeight: Double){
        this.weight = weight
        this.countWeight = countWeight
    }

    fun countSVector(value: Double, scaleWeight: Double){
        this.value = value
        this.scaleWeight = scaleWeight
    }

    fun countVVector(preferenceValue: Double, totalPreferenceValue: Double){
        this.preferenceValue = preferenceValue
        this.totalPreferenceValue = totalPreferenceValue
    }

    fun getAlternative() = dataRepository.getAlternative().asLiveData()

    fun getCriteria() = dataRepository.getCriteria().asLiveData()

    fun getAlternativeValue() = dataRepository.getAlternativeValue().asLiveData()

    fun eachWeight() = weight/countWeight

    fun preferenceValue(): Double {
        if (state == "Manfaat"){
           return value.pow(scaleWeight)
        }else{
            return value.pow(-scaleWeight)
        }

    }

    fun vectorValue() = preferenceValue/totalPreferenceValue

    fun getResult() = dataRepository.getResult().asLiveData()

    fun insertResult(result: ResultEntity) = GlobalScope.launch {
        dataRepository.insertResult(result)
    }

    fun deleteResult() = GlobalScope.launch {
        dataRepository.deleteResult()
    }

    fun getPreferenceValue() = dataRepository.getPreferenceValue().asLiveData()

}