package br.com.lucasfranco.di

import br.com.lucasfranco.common.ResourceManager
import br.com.lucasfranco.common.navigation.Navigation
import br.com.lucasfranco.common.navigation.NavigationImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule = module {
    single { ResourceManager(androidContext()) }
    single<Navigation> { NavigationImpl() }
}


val appModules: List<Module> = mutableListOf(
    appModule
)