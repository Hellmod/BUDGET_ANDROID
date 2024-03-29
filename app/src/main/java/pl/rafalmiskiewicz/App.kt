package pl.rafalmiskiewicz

import android.app.Application
import androidx.databinding.library.BuildConfig
import com.chibatching.kotpref.Kotpref
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import pl.rafalmiskiewicz.util.di.adapterModule
import pl.rafalmiskiewicz.util.di.appModule
import pl.rafalmiskiewicz.util.di.repository
import pl.rafalmiskiewicz.util.di.viewModelModule
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeTimber()
        initializeKotPref()
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
                    viewModelModule,
                    repository,
                    appModule,
                    adapterModule
                )
            )
        }
    }

    private fun initializeKotPref() {
        Kotpref.init(this)
    }
}