package com.fghilmany.kuliahyuk.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "preference_entity")
data class PreferenceEntity (
    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "id")
    var id : Int = 0,

    @ColumnInfo(name = "id_alternative")
    var idAlternative : Int = 0,

    @ColumnInfo(name = "value")
    var value: Double = 0.0
)