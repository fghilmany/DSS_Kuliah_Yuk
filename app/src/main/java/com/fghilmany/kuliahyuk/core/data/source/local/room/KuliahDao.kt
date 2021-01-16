package com.fghilmany.kuliahyuk.core.data.source.local.room

import androidx.room.*
import com.fghilmany.kuliahyuk.core.data.source.local.entity.*
//import com.fghilmany.kuliahyuk.core.data.source.local.entity.AlternativeWithCriteriaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface KuliahDao {

    //criteria
    @Query("SELECT * FROM criteria_entity")
    fun getCriteria(): Flow<List<CriteriaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCriteria(criteria: CriteriaEntity)

    @Query("DELETE FROM criteria_entity")
    suspend fun deleteAllCriteria()

    @Query("DELETE FROM criteria_entity WHERE id == :id")
    suspend fun deleteCriteriaById(id: Int)

    //alternative
    @Query("SELECT * FROM alternative_entity")
    fun getAlternative(): Flow<List<AlternativeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertalternative(alternative: AlternativeEntity)

    @Query("DELETE FROM alternative_entity")
    suspend fun deleteAllAlternative()

    @Query("DELETE FROM alternative_entity WHERE id == :id")
    suspend fun deleteAlternativeById(id: Int)

   /* //alternative with criteria
    @Transaction
    @Query("SELECT * FROM alternative_entity, criteria_entity")
    fun getAlternativeWithCriteria(): Flow<List<AlternativeWithCriteriaEntity>>
*/
    //value
    @Query("SELECT * FROM value_entity")
    fun getAlternativeValue(): Flow<List<AlternativeValueEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlternativeValue(alternativeValue: AlternativeValueEntity)

    @Query("DELETE FROM value_entity")
    suspend fun deleteAllAlternativeValue()

    @Query("DELETE FROM value_entity WHERE id == :id")
    suspend fun deleteAlternativeValueById(id: Int)

    //result
    @Query("SELECT * FROM result_entity ORDER BY result DESC")
    fun getResult(): Flow<List<ResultEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(result: ResultEntity)

    @Query("DELETE FROM result_entity")
    suspend fun deleteResult()

    //result
    @Query("SELECT * FROM preference_entity")
    fun getPreference(): Flow<List<PreferenceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPreference(preference: PreferenceEntity)

    @Query("DELETE FROM preference_entity")
    suspend fun deletePreference()

    //result
    @Query("SELECT * FROM preference_value_entity")
    fun getPreferenceValue(): Flow<List<PreferenceValueEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPreferenceValue(result: PreferenceValueEntity)

    @Query("DELETE FROM preference_value_entity")
    suspend fun deletePreferenceValue()
}