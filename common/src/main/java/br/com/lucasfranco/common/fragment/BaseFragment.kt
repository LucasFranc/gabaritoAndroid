package br.com.lucasfranco.common.fragment

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import br.com.lucasfranco.common.activity.CoreBaseActivity
import br.com.lucasfranco.common.config.ViewConfig
import br.com.lucasfranco.util.safeHeritage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.core.module.Module
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment :
    Fragment(),
    CoroutineScope,
    ViewConfig,
    Toolbar.OnMenuItemClickListener {

    abstract fun tag(): String

    abstract fun initialize()

    open fun isSecurityScreen(): Boolean = false

    @LayoutRes
    open fun getSkeletonLayoutRes(): Int? = null

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // using to block print and not show image in recent apps
        if (isSecurityScreen()) {
            activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_SECURE)
        } else {
            activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        bindToolbar()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    open fun autoSetColorStatusBar(): Boolean = true

    fun getActivityForError(): CoreBaseActivity? = activity?.safeHeritage()

    open fun getModule(): Module? {
        return null
    }

    private fun bindToolbar() {
        view?.post {
            getToolbar()?.apply {
                if (!showHomeAsUp) {
                    navigationIcon = null
                }
                if (!showDisplayShowTitle) {
                    title = null
                }
                this@BaseFragment.menu?.let { menuLet ->
                    inflateMenu(menuLet)
                }
                setNavigationOnClickListener(::onNavigationClick)
                setOnMenuItemClickListener(this@BaseFragment)
            }
        }
    }

    override fun onMenuItemClick(item: MenuItem): Boolean =
        onOptionsItemSelected(item)

    open fun onNavigationClick(view: View) {
        /*nothing*/
    }

    @MenuRes
    open val menu: Int? = null

    open fun getToolbar(): Toolbar? = null

    open val showHomeAsUp: Boolean = true

    open val showDisplayShowTitle: Boolean = false
}