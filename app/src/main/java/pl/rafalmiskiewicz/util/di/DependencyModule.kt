package pl.rafalmiskiewicz.util.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import pl.rafalmiskiewicz.data.source.HttpClientFactory
import pl.rafalmiskiewicz.data.source.local.AppPreferences
import pl.rafalmiskiewicz.data.source.local.CredentialStore
import pl.rafalmiskiewicz.ui.MainViewModel
import pl.rafalmiskiewicz.ui.hours.HoursViewModel
import pl.rafalmiskiewicz.ui.login.LoginViewModel
import pl.rafalmiskiewicz.util.api.AdozlApi
import pl.rafalmiskiewicz.util.api.MainRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

val viewModelModule: Module = module {
    viewModel { MainViewModel() }
    viewModel { LoginViewModel(get()) }
    viewModel { HoursViewModel(get()) }
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

    return Retrofit.Builder()
        .baseUrl("http://my-json-server.typicode.com/Hellmod/api_android/")
        .client(httpClientFactory.getHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun provideToyotaApi(retrofit: Retrofit): AdozlApi = retrofit.create(AdozlApi::class.java)