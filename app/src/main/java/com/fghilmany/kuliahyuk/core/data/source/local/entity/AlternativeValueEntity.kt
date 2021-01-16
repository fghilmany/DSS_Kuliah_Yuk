package com.fghilmany.kuliahyuk.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "value_entity")
data class AlternativeValueEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id : Int = 0,

    @ColumnInfo(name = "id_alternative")
    var idAlternative : Int = 0,

    @ColumnInfo(name = "id_Criteria")
    var idCriteria : Int = 0,

    @ColumnInfo(name = "value")
    var value: Int = 0
)