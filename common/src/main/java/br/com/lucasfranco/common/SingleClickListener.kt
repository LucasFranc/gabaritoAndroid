package br.com.lucasfranco.common

import android.os.SystemClock
import android.view.View
import br.com.lucasfranco.util.Const

class SingleClickListener(
    private val customClickInterval: Int = Const.Utils.SINGLE_CLICK_INTERVAL,
    private val onSingleClick: (View) -> Unit
) : View.OnClickListener {

    private var lastTimeClicked = 0L

    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked > customClickInterval) {
            lastTimeClicked = SystemClock.elapsedRealtime()
            onSingleClick(v)
        }
    }
}