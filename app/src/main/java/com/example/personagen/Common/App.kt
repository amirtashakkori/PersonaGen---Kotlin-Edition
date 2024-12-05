package com.example.personagen.Common

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.personagen.Data.DataBase.AppDataBase
import com.example.personagen.Data.Model.ApiService
import com.example.personagen.Data.Model.getApiServiceInstance
import com.example.personagen.Data.UserRepository
import com.example.personagen.GenerateUser.GenerateViewModel
import com.example.personagen.Main.MainViewModel
import com.example.personagen.UserInfo.UserInfoActivity
import com.example.personagen.UserInfo.UserViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        val myModules = module {
            single { getApiServiceInstance() }
            single { AppDataBase.getInstance(get()).getDao() }
            single { UserRepository(get(), get()) }

            viewModel { MainViewModel(get()) }
            viewModel { GenerateViewModel(get()) }
            viewModel { UserViewModel(get()) }

        }

        startKoin {
            androidContext(this@App)
            modules(myModules)
        }

    }

}