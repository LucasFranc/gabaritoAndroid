package br.com.lucasfranco.common.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import br.com.lucasfranco.common.ViewIds

abstract class BaseJetNavigationActivity : CoreBaseActivity() {

    @LayoutRes
    open fun getLayoutId(): Int? = null
    open fun navGraphId(): Int? = null

    open val navHostFragmentDefault: NavHostFragment by lazy {
        supportFragmentManager
            .findFragmentById(ViewIds.navHostId) as NavHostFragment
    }

    override fun initialize(savedInstanceState: Bundle?) {
        getLayoutId()?.let { layoutId ->
            setContentView(layoutId)
            navGraphId()?.let { navGraphId ->
                navHostFragmentDefault.apply {
                    checkNotNull(findNavController().setGraph(navGraphId))
                }
            }
        }
    }
}