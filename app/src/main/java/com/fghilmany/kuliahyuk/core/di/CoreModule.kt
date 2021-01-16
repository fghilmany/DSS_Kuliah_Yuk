package com.fghilmany.kuliahyuk.core.di

import androidx.room.Room
import com.fghilmany.kuliahyuk.core.data.DataRepository
import com.fghilmany.kuliahyuk.core.data.IDataRepository
import com.fghilmany.kuliahyuk.core.data.source.local.LocalDatasource
import com.fghilmany.kuliahyuk.core.data.source.local.room.KuliahDatabase
import com.fghilmany.kuliahyuk.core.utils.AppExecutors
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val databaseModule = module {
    factory { get<KuliahDatabase>().kuliahDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            KuliahDatabase::class.java, "kuliah_db"
        ).fallbackToDestructiveMigration().build()
    }
}

val repositoryModule = module {
    single { LocalDatasource(get()) }
    factory { AppExecutors() }
    single/*<IDataRepository>*/ {
        DataRepository(
            get(),
            get()
        )
    }
}
