package com.fghilmany.kuliahyuk.core.data.source.local

import com.fghilmany.kuliahyuk.core.data.source.local.entity.*
import com.fghilmany.kuliahyuk.core.data.source.local.room.KuliahDao
import kotlinx.coroutines.flow.Flow

class LocalDatasource (private val kuliahDao: KuliahDao){

//    fun getAlternativeWithCriteria(): Flow<List<AlternativeWithCriteriaEntity>> = kuliahDao.getAlternativeWithCriteria()

    fun getCriteria(): Flow<List<CriteriaEntity>> = kuliahDao.getCriteria()

    suspend fun insertCriteria(criteria: CriteriaEntity) = kuliahDao.insertCriteria(criteria)

    suspend fun deleteAllCriteria() = kuliahDao.deleteAllCriteria()

    suspend fun deleteCriteriaById(id: Int) = kuliahDao.deleteCriteriaById(id)

    fun getAlternative(): Flow<List<AlternativeEntity>> = kuliahDao.getAlternative()

    suspend fun insertAlternative(alternative: AlternativeEntity) = kuliahDao.insertalternative(alternative)

    suspend fun deleteAllAlternative() = kuliahDao.deleteAllAlternative()

    suspend fun deleteAlternativeById(id: Int) = kuliahDao.deleteAlternativeById(id)

    fun getAlternativeValue(): Flow<List<AlternativeValueEntity>> = kuliahDao.getAlternativeValue()

    suspend fun insertAlternativeValue(alternativeValue: AlternativeValueEntity) = kuliahDao.insertAlternativeValue(alternativeValue)

    suspend fun deleteAllAlternativeValue() = kuliahDao.deleteAllAlternativeValue()

    suspend fun deleteAlternativeValueById(id: Int) = kuliahDao.deleteAlternativeValueById(id)

    fun getResult(): Flow<List<ResultEntity>> = kuliahDao.getResult()

    suspend fun insertResult(result: ResultEntity) = kuliahDao.insertResult(result)

    suspend fun deleteResult() = kuliahDao.deleteResult()

    fun getPreference(): Flow<List<PreferenceEntity>> = kuliahDao.getPreference()

    suspend fun insertPreference(result: PreferenceEntity) = kuliahDao.insertPreference(result)

    suspend fun deletePreference() = kuliahDao.deletePreference()

    fun getPreferenceValue(): Flow<List<PreferenceValueEntity>> = kuliahDao.getPreferenceValue()

    suspend fun insertPreferenceValue(result: PreferenceValueEntity) = kuliahDao.insertPreferenceValue(result)

    suspend fun deletePreferenceValue() = kuliahDao.deletePreferenceValue()
}