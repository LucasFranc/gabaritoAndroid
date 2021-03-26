package br.com.lucasfranco.login.presentation

import android.view.View
import br.com.lucasfranco.common.fragment.BaseViewModelFragment
import br.com.lucasfranco.login.BR
import br.com.lucasfranco.login.LoginViewModel
import br.com.lucasfranco.login.R
import br.com.lucasfranco.login.databinding.FragmentLoginBinding
import org.koin.android.ext.android.inject

class LoginFragment : BaseViewModelFragment<FragmentLoginBinding, LoginViewModel>() {

    override val getLayoutId: Int = R.layout.fragment_login
    override val bindingVariable: Int? = BR.loginViewModel
    override val viewModel: LoginViewModel by inject()
    override fun tag(): String = LoginFragment::class.java.name

    override fun initialize() {
        viewModel.initializer { ::onClickItem(it) }
    }

    fun onClickItem(v : View){
        when(v.id){

        }
    }

}