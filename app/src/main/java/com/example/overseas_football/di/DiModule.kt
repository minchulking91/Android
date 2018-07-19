package com.example.overseas_football.di

import com.example.overseas_football.network.ApiManager
import com.example.overseas_football.network.RetrofitService
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

val apiModule: Module = applicationContext {
    factory { Presenter(get()) }
    bean { ApiManager(get()) as RetrofitService }
}