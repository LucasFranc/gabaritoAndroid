package br.com.lucasfranco.common

import android.view.View
import androidx.databinding.BindingAdapter
import br.com.lucasfranco.common.viewmodel.BaseViewModel
import br.com.lucasfranco.util.Const

/**
 *  Use this extension-function to prevent 'common double tap' issue
 *  when you're pressing button twice very fast and double action is called.
 *  @param customClickInterval custom millis this click listener debounce its calls
 *  @param onSingleClick callback
 */
fun View.setSingleClickListener(
        customClickInterval: Int = Const.Utils.SINGLE_CLICK_INTERVAL,
        onSingleClick: (View) -> Unit
) {
    val singleClickListener = SingleClickListener(customClickInterval) {
        onSingleClick(it)
    }
    setOnClickListener(singleClickListener)
}

@BindingAdapter("implementSingleClickListener")
fun View.implementSingleClickListener(baseViewModel: BaseViewModel) {
    setSingleClickListener(onSingleClick = baseViewModel::onItemClicked)
}