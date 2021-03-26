package br.com.lucasfranco.common.navigation

import android.view.MenuItem
import br.com.lucasfranco.common.config.NavigationListener
import br.com.lucasfranco.common.fragment.BaseFragment

abstract class BaseNavigationFragment : BaseFragment() {

    var clickListener: NavigationListener? = null

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            android.R.id.home -> {
                callOnHomeButtonToolbarClick()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    open fun callOnHomeButtonToolbarClick() {
        callOnBackButton()
    }

    open fun callOnBackButton() {
        clickListener?.onBackButtonClick()
    }

    open fun onFragmentVisible(): Unit = Unit

    open fun onBackPressedFromNavigation(): Unit = Unit
}