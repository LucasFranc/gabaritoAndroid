package br.com.lucasfranco.common.navigation

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import br.com.lucasfranco.common.R
import br.com.lucasfranco.common.config.NavigationListener
import br.com.lucasfranco.common.fragment.BaseFragment
import br.com.lucasfranco.common.fragment.attachFragmentWithAnimationAllowingStateLoss
import br.com.lucasfranco.util.safeHeritage

abstract class GenericNavigation constructor(
    private val activity: AppCompatActivity
) : NavigationListener {

    abstract fun getMaxNavigationItems(): Int

    private fun makeFragmentAttach(
        fragmentInstance: BaseNavigationFragment,
        addToBackStack: Boolean = true,
        replace: Boolean = false
    ) =
        fragmentInstance.attachFragmentWithAnimationAllowingStateLoss(
            activity.supportFragmentManager,
            R.id.nav_host,
            addToBackStack,
            replace = replace
        )

    override fun requestPermissions(permision: List<String>, codePermisionRequest: Int) {
        if (
            !permision.map { ContextCompat.checkSelfPermission(activity, it) }
                .all { it == PackageManager.PERMISSION_GRANTED }
        ) {
            ActivityCompat.requestPermissions(
                activity,
                permision.toTypedArray(),
                codePermisionRequest
            )
        } else {
            onRequestPermissionsResult(
                codePermisionRequest,
                permision.toTypedArray(),
                intArrayOf(PackageManager.PERMISSION_GRANTED)
            )
        }
    }

    open fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val fragment: Fragment? =
            activity.supportFragmentManager.fragments.safeHeritage<BaseFragment>().firstOrNull {
                it.getRequestCode().contains(requestCode)
            }
        fragment?.onActivityResult(requestCode, resultCode, data)
    }

    open fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        val fragment: Fragment? =
            activity.supportFragmentManager.fragments.safeHeritage<BaseFragment>().firstOrNull {
                it.getRequestPermisionCode().contains(requestCode)
            }
        fragment?.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    open fun onBackPressed() {
        when {
            activity.supportFragmentManager.backStackEntryCount > 1 -> {
                activity.supportFragmentManager.fragments.firstOrNull { it.isVisible }
                    ?.let { fragment ->
                        when (fragment) {
                            is BaseNavigationFragment -> fragment.onBackPressedFromNavigation()
                        }
                    }
                activity.supportFragmentManager.popBackStackImmediate()
                activity.supportFragmentManager.fragments.lastOrNull()?.let { fragment ->
                    fragment.view?.post {
                        when (fragment) {
                            is BaseNavigationFragment -> {
                                fragment.onFragmentVisible()
                            }
                        }
                    }
                }
            }
            else -> {
                activity.supportFragmentManager.fragments.firstOrNull { it.isVisible }
                    ?.let { fragment ->
                        when (fragment) {
                            is BaseNavigationFragment -> fragment.onBackPressedFromNavigation()
                        }
                    }
                activity.finish()
            }
        }
    }

    protected fun showFragment(
        fragment: BaseNavigationFragment,
        addToBackStack: Boolean = true,
        replace: Boolean = false
    ) {
        makeFragmentAttach(fragment, addToBackStack, replace)
    }

    override fun onBackButtonClick() {
        onBackPressed()
    }
}