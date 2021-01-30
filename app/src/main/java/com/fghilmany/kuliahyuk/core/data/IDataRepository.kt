package com.fghilmany.kuliahyuk.core.data

import com.fghilmany.kuliahyuk.core.data.source.local.entity.*
//import com.fghilmany.kuliahyuk.core.data.source.local.entity.AlternativeWithCriteriaEntity
import kotlinx.coroutines.flow.Flow

interface IDataRepository {

//    fun getAlternativeWithCriteria(): Flow<List<AlternativeWithCriteriaEntity>>

    fun getCriteria(): Flow<List<CriteriaEntity>>

    suspend fun insertCriteria(criteria: CriteriaEntity)

    suspend fun deleteAllCriteria()

    suspend fun deleteCriteriaById(id: Int)

    fun getAlternative(): Flow<List<AlternativeEntity>>

    suspend fun insertAlternative(alternative: AlternativeEntity)

    suspend fun deleteAllAlternative()

    suspend fun deleteAlternativeById(id: Int)

    fun getAlternativeValue(): Flow<List<AlternativeValueEntity>>

    fun getAlternativeValueById(id: Int): Flow<List<AlternativeValueEntity>>


    suspend fun insertAlternativeValue(alternative: AlternativeValueEntity)

    suspend fun deleteAllAlternativeValue()

    suspend fun deleteAlternativeValueById(id: Int)

    fun getResult(): Flow<List<ResultEntity>>

    suspend fun insertResult(result: ResultEntity)

    suspend fun deleteResult()

    suspend fun deleteResultById(id: Int)

    fun getPreference(): Flow<List<PreferenceEntity>>

    suspend fun insertPreference(result: PreferenceEntity)

    suspend fun deletePreference()

    suspend fun deletePreferenceById(id: Int)

    fun getPreferenceValue(): Flow<List<PreferenceValueEntity>>

    suspend fun insertPreferenceValue(result: PreferenceValueEntity)

    suspend fun deletePreferenceValue()

    suspend fun deletePreferenceValueById(id: Int)

}
