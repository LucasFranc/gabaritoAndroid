package br.com.lucasfranco.login.presentation

import android.os.Bundle
import br.com.lucasfranco.common.activity.BaseJetNavigationActivity
import br.com.lucasfranco.login.R
import br.com.lucasfranco.login.di.loginViewModelModule
import org.koin.core.module.Module

class LoginActivity : BaseJetNavigationActivity() {

    override fun getModule(): List<Module>? = listOf(loginViewModelModule)

    override fun navGraphId(): Int? = R.navigation.nav_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}