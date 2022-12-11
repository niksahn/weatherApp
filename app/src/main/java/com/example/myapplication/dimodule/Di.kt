package com.example.myapplication.dimodule
import org.koin.dsl.module
import com.example.myapplication.Constants
import com.example.myapplication.data.api.ApiCurrent
import com.example.myapplication.data.api.ApiForecast

import com.example.myapplication.data.api.repository.ApiRepositoryImpl
import com.example.myapplication.domain.interactor.Interactor
import com.example.myapplication.domain.interactor.InteractorImpl
import com.example.myapplication.domain.repository.ApiRepository
import com.example.myapplication.ui.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single<ApiRepository> { ApiRepositoryImpl(get(),get()) }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiForecast::class.java)

    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiCurrent::class.java)

    }


}
val domainModule = module {
    factory<Interactor> { InteractorImpl(get()) }

}
val viewModelModule = module {
    viewModel {
        ViewModel(get())
    }
}
