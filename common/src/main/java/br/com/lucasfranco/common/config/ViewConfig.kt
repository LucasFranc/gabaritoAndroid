package br.com.lucasfranco.common.config

import android.content.pm.ActivityInfo

interface ViewConfig {

    fun getOrientation(): Int = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    fun getRequestCode(): MutableList<Int> = emptyArray<Int>().toMutableList()
    fun getRequestPermisionCode(): MutableList<Int> = emptyArray<Int>().toMutableList()
    fun updateView() = Unit
}