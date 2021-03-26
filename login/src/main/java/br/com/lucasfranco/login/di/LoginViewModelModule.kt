package br.com.lucasfranco.login.di

import br.com.lucasfranco.login.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginViewModelModule = module {
    viewModel { LoginViewModel(get()) }
}