package pl.rafalmiskiewicz.util.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import pl.rafalmiskiewicz.ui.MainViewModel
import pl.rafalmiskiewicz.ui.login.LoginViewModel
import pl.rafalmiskiewicz.util.api.AdozlApi
import pl.rafalmiskiewicz.util.api.MainRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule: Module = module {
    viewModel { MainViewModel() }
    viewModel { LoginViewModel(get()) }
}

val repository = module {
    factory { MainRepository(get()) }
}

val appModule: Module = module {
    single { provideRetrofit() }
    single { provideToyotaApi(get()) }
}

private fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("http://my-json-server.typicode.com/Hellmod/api_android/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun provideToyotaApi(retrofit: Retrofit): AdozlApi = retrofit.create(AdozlApi::class.java)