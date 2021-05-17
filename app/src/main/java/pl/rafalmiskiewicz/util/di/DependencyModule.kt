package pl.rafalmiskiewicz.util.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import pl.rafalmiskiewicz.ui.MainViewModel
import pl.rafalmiskiewicz.ui.login.LoginViewModel

val viewModelModule: Module = module {
    viewModel { MainViewModel() }
    viewModel { LoginViewModel() }
}