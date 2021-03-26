package br.com.lucasfranco.common.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.com.lucasfranco.common.navigation.GenericNavigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import kotlin.coroutines.CoroutineContext

abstract class CoreBaseActivity :
    AppCompatActivity(),
    CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    abstract fun initialize(savedInstanceState: Bundle?)

    open fun getNavigation(): GenericNavigation? = null

    private fun bindToolbar() {
        getToolbar()?.let { toolbar ->
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(showHomeAsUp())
            supportActionBar?.setDisplayShowTitleEnabled(showDisplayShowTitle())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectFeature()
        super.onCreate(savedInstanceState)

        initialize(savedInstanceState)
        bindToolbar()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        getNavigation()?.onActivityResult(requestCode, resultCode, data)
            ?: super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onBackPressed() {
        getNavigation()?.onBackPressed() ?: super.onBackPressed()
    }

    open fun getToolbar(): Toolbar? = null

    open fun showHomeAsUp(): Boolean = true

    open fun showDisplayShowTitle(): Boolean = false

    open fun getModule(): List<Module>? = null

    private val loadFeature by lazy {
        getModule()?.let { modulesList ->
            loadKoinModules(modulesList)
            modulesList
        }
    }

    private fun injectFeature() = loadFeature
}