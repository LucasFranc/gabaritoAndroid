package br.com.lucasfranco.common.fragment

import androidx.fragment.app.FragmentManager
import br.com.lucasfranco.common.fragment.AnimationTransactionObject.enterFragment
import br.com.lucasfranco.common.fragment.AnimationTransactionObject.exitFragment
import br.com.lucasfranco.common.fragment.AnimationTransactionObject.popEnter
import br.com.lucasfranco.common.fragment.AnimationTransactionObject.popExit
import br.com.lucasfranco.common.navigation.BaseNavigationFragment

fun BaseNavigationFragment.attachFragmentWithAnimationAllowingStateLoss(
    fragmentManager: FragmentManager,
    containerId: Int,
    addToBackStack: Boolean,
    replace: Boolean = false
) {
    val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.setCustomAnimations(
            enterFragment,
            exitFragment,
            popEnter,
            popExit
        )

    if (addToBackStack && !replace) {
        fragmentTransaction.addToBackStack(tag())
    } else if (replace) {
        fragmentTransaction.addToBackStack(null)
    }

    if (replace) {
        fragmentTransaction.replace(containerId, this, tag())
    } else {
        fragmentTransaction.add(containerId, this, tag())
    }
    fragmentTransaction.commitAllowingStateLoss()
}