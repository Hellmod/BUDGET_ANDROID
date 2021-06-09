package pl.rafalmiskiewicz.util.di

import com.google.gson.GsonBuilder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import pl.rafalmiskiewicz.data.source.HttpClientFactory
import pl.rafalmiskiewicz.data.source.local.AppPreferences
import pl.rafalmiskiewicz.data.source.local.CredentialStore
import pl.rafalmiskiewicz.ui.MainViewModel
import pl.rafalmiskiewicz.ui.hours.HoursAdapter
import pl.rafalmiskiewicz.ui.hours.HoursViewModel
import pl.rafalmiskiewicz.ui.login.LoginViewModel
import pl.rafalmiskiewicz.ui.schedules.SchedulesAdapter
import pl.rafalmiskiewicz.ui.schedules.SchedulesViewModel
import pl.rafalmiskiewicz.util.api.AdozlApi
import pl.rafalmiskiewicz.util.api.MainRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val adapterModule = module {
    factory { HoursAdapter() }
    factory { SchedulesAdapter() }
}

val viewModelModule: Module = module {
    viewModel { MainViewModel() }
    viewModel { LoginViewModel(get(),get())}
    viewModel { HoursViewModel(get()) }
    viewModel { SchedulesViewModel(get()) }
}

val repository = module {
    factory { MainRepository(get()) }
}

val appModule: Module = module {
    single { AppPreferences() }
    single { provideRetrofit(get()) }
    single { provideToyotaApi(get()) }
    single { HttpClientFactory(get()) }
    single { CredentialStore(get()) }
}

private fun provideRetrofit(httpClientFactory: HttpClientFactory): Retrofit {

    val gson = GsonBuilder()
        .setDateFormat("MMM dd, yyyy, hh:mm:ss aa")
        .create()

    return Retrofit.Builder()
        .baseUrl("http://my-json-server.typicode.com/Hellmod/api_android/")// http://192.168.0.107:8080/api/ ||  http://my-json-server.typicode.com/Hellmod/api_android/
        .client(httpClientFactory.getHttpClient())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

private fun provideToyotaApi(retrofit: Retrofit): AdozlApi = retrofit.create(AdozlApi::class.java)