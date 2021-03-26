package br.com.lucasfranco.exemploarquitetura

import android.app.Application
import br.com.lucasfranco.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ApplicationManager : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidLogger()
            androidContext(this@ApplicationManager)
            androidFileProperties()
            koin.loadModules(appModules)
        }
    }

}