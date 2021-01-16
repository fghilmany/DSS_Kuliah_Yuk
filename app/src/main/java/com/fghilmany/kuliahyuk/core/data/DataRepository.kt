package com.fghilmany.kuliahyuk.core.data

import com.fghilmany.kuliahyuk.core.data.source.local.LocalDatasource
import com.fghilmany.kuliahyuk.core.data.source.local.entity.*
//import com.fghilmany.kuliahyuk.core.data.source.local.entity.AlternativeWithCriteriaEntity
import com.fghilmany.kuliahyuk.core.utils.AppExecutors
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DataRepository (
    private val localDatasource: LocalDatasource,
    private val appExecutors: AppExecutors
): IDataRepository{
//    override fun getAlternativeWithCriteria(): Flow<List<AlternativeWithCriteriaEntity>> =
//        localDatasource.getAlternativeWithCriteria()

    override fun getCriteria(): Flow<List<CriteriaEntity>> =
        localDatasource.getCriteria()

    override suspend  fun insertCriteria(criteria: CriteriaEntity) =
        localDatasource.insertCriteria(criteria)

    override suspend fun deleteAllCriteria() =
        localDatasource.deleteAllCriteria()

    override suspend fun deleteCriteriaById(id: Int) =
        localDatasource.deleteCriteriaById(id)

    override fun getAlternative(): Flow<List<AlternativeEntity>> =
        localDatasource.getAlternative()

    override suspend  fun insertAlternative(alternative: AlternativeEntity) =
        localDatasource.insertAlternative(alternative)

    override suspend fun deleteAllAlternative() =
        localDatasource.deleteAllAlternative()

    override suspend fun deleteAlternativeById(id: Int) =
        localDatasource.deleteAlternativeById(id)

    override fun getAlternativeValue(): Flow<List<AlternativeValueEntity>> =
        localDatasource.getAlternativeValue()

    override suspend  fun insertAlternativeValue(alternative: AlternativeValueEntity) =
        localDatasource.insertAlternativeValue(alternative)

    override suspend fun deleteAllAlternativeValue() =
        localDatasource.deleteAllAlternativeValue()

    override suspend fun deleteAlternativeValueById(id: Int) =
        localDatasource.deleteAlternativeValueById(id)

    override fun getResult(): Flow<List<ResultEntity>> =
        localDatasource.getResult()

    override suspend fun insertResult(result: ResultEntity) =
        localDatasource.insertResult(result)

    override suspend fun deleteResult() =
        localDatasource.deleteResult()

    override fun getPreference(): Flow<List<PreferenceEntity>> =
        localDatasource.getPreference()


    override suspend fun insertPreference(result: PreferenceEntity) =
        localDatasource.insertPreference(result)

    override suspend fun deletePreference() =
        localDatasource.deletePreference()

    override fun getPreferenceValue(): Flow<List<PreferenceValueEntity>> =
        localDatasource.getPreferenceValue()

    override suspend fun insertPreferenceValue(result: PreferenceValueEntity) =
        localDatasource.insertPreferenceValue(result)

    override suspend fun deletePreferenceValue() =
        localDatasource.deletePreferenceValue()
}
