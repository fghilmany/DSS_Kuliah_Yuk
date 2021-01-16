package com.fghilmany.kuliahyuk.ui.alternative

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fghilmany.kuliahyuk.core.data.DataRepository
import com.fghilmany.kuliahyuk.core.data.source.local.entity.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.pow

class AlternativeViewModel (private val dataRepository: DataRepository): ViewModel(){

    fun insertAlternative(alternative: AlternativeEntity) = GlobalScope.launch {
        dataRepository.insertAlternative(alternative)
    }

    fun insertAlternativeValue(alternativeValue: AlternativeValueEntity) = GlobalScope.launch {
        dataRepository.insertAlternativeValue(alternativeValue)
    }

    fun getCriteria() = dataRepository.getCriteria().asLiveData()

    fun getAlternative() = dataRepository.getAlternative().asLiveData()

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

    fun insertPreferenceValue(preverenceValue: PreferenceValueEntity) = GlobalScope.launch {
        dataRepository.insertPreferenceValue(preverenceValue)
    }

    fun getPreferenceValue() = dataRepository.getPreferenceValue().asLiveData()

    fun insertPreference(preverence: PreferenceEntity) = GlobalScope.launch {
        dataRepository.insertPreference(preverence)
    }

    fun getPreference() = dataRepository.getPreference().asLiveData()

}