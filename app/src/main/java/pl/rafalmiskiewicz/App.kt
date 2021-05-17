package pl.rafalmiskiewicz

import android.app.Application
import androidx.databinding.library.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import pl.rafalmiskiewicz.util.di.viewModelModule
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeTimber()
        initializeDependencyInjections()
    }

    private fun initializeTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initializeDependencyInjections() {
        startKoin {
            androidContext(this@App)
            modules(
                modules = listOf(
                    viewModelModule
                )
            )
        }
    }
}