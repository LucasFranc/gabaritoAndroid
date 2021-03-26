package br.com.lucasfranco.common

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.annotation.ArrayRes
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

class ResourceManager constructor(val context: Context) {

    fun getString(@StringRes stringRes: Int): String =
        context.resources.getString(stringRes)

    fun getQuantityString(stringRes: Int, quantity: Int): String =
        context.resources.getQuantityString(stringRes, quantity, quantity)

    @Suppress("SpreadOperator")
    fun getString(@StringRes stringRes: Int, vararg textToFormat: Any): String =
        context.resources.getString(stringRes, *textToFormat)

    fun getDrawable(@DrawableRes drawableRes: Int): Drawable? =
        ContextCompat.getDrawable(context, drawableRes)

    fun getDimension(@DimenRes dimensionRes: Int): Float =
        context.resources.getDimension(dimensionRes)

    fun getColor(@ColorRes color: Int): Int =
        ContextCompat.getColor(context, color)

    fun getFont(@FontRes fontFamily: Int): Typeface? =
        ResourcesCompat.getFont(context, fontFamily)

    fun getAssets(): AssetManager =
        context.assets

    fun getArray(@ArrayRes resId: Int): Array<String> =
        context.resources.getStringArray(resId)
}