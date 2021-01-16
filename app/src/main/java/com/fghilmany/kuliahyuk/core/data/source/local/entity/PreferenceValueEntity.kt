package com.fghilmany.kuliahyuk.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "preference_value_entity")
data class PreferenceValueEntity (
    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "id")
    var id : Int = 0,

    @ColumnInfo(name = "name_alternative")
    var nameAlternative: String = "",

    @ColumnInfo(name = "value")
    var preferenceValue : Double = 0.0
)