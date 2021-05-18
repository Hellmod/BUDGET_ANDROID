package pl.rafalmiskiewicz.util.di

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import pl.rafalmiskiewicz.LoginUseCase
import pl.rafalmiskiewicz.ui.MainViewModel
import pl.rafalmiskiewicz.ui.login.LoginViewModel
import pl.rafalmiskiewicz.util.api.user.UserRepo
import pl.rafalmiskiewicz.util.api.user.UserRepoImpl
import pl.rafalmiskiewicz.util.jsonAdapter.LocalDateJsonAdapter
import pl.rafalmiskiewicz.util.jsonAdapter.LocalDateTimeJsonAdapter
import pl.rafalmiskiewicz.util.jsonAdapter.YearMonthJsonAdapter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val viewModelModule: Module = module {
    viewModel { MainViewModel() }
    viewModel { LoginViewModel(get(),get(),get()) }
}

val appModule: Module = module {
    single { provideRetrofit() }
}

val useCaseModule  = module {
    factory { LoginUseCase(get(), get(), get()) }
}

val repoModule = module {
    factory<UserRepo> { UserRepoImpl(get()) }
}

private fun provideRetrofit(): Retrofit {
    val httpClient = OkHttpClient()

    val moshi = Moshi.Builder()
        .add(LocalDateJsonAdapter())
        .add(LocalDateTimeJsonAdapter())
        .add(YearMonthJsonAdapter())
        .build()

    return Retrofit.Builder()
        .baseUrl("http://localhost:8080/")
        .client(httpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}