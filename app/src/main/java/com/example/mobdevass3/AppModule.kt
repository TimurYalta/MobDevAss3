package com.example.mobdevass3

import com.example.mobdevass3.data.AnimalRepository
import com.example.mobdevass3.data.localDB.AnimalDatabase
import com.example.mobdevass3.data.service.RestClient
import com.example.mobdevass3.domain.AnimalInteractor
import com.example.mobdevass3.presentation.mainview.MainViewModel
import com.example.mobdevass3.utils.Consts.API_BASE_URL
import org.koin.dsl.module

fun createAppModule(baseUrl: String = API_BASE_URL) = module {
    single { RestClient.getInstance(baseUrl) }
    single { AnimalInteractor(get()) }
    single { MainViewModel(get(), get()) }
    single { AnimalRepository(get(), get()) }
    single { AnimalDatabase.getInstance(get()) }
}
