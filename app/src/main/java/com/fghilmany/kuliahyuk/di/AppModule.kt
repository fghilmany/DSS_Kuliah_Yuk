package com.fghilmany.kuliahyuk.di


import com.fghilmany.kuliahyuk.ui.alternative.AlternativeViewModel
import com.fghilmany.kuliahyuk.ui.criteria.CriteriaViewModel
import com.fghilmany.kuliahyuk.ui.dashboard.DashboardViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DashboardViewModel(get()) }
    viewModel { AlternativeViewModel(get()) }
    viewModel { CriteriaViewModel(get()) }
}
