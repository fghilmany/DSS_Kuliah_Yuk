package com.fghilmany.kuliahyuk.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fghilmany.kuliahyuk.core.data.source.local.entity.*

@Database(
    entities = [AlternativeEntity::class,
        CriteriaEntity::class,
        AlternativeValueEntity::class,
        ResultEntity::class,
        PreferenceValueEntity::class,
        PreferenceEntity::class],
    version = 1,
    exportSchema = false
)
abstract class KuliahDatabase : RoomDatabase() {
    abstract fun kuliahDao(): KuliahDao
}