package com.fghilmany.kuliahyuk.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "result_entity")
data class ResultEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id : Int = 0,

    @ColumnInfo(name = "name_university")
    var name_university: String = "",

    @ColumnInfo(name = "name_alternative")
    var nameAlternative: String = "",

    @ColumnInfo(name = "result")
    var result: Double = 0.0
)