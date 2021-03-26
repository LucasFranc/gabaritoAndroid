package br.com.lucasfranco.common.dialogs

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AlertDialog
import br.com.lucasfranco.common.R

class LoadingDialog(
    context: Context
) : AlertDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            super.onCreate(savedInstanceState)
        }
        setContentView(R.layout.dialog_loading)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun show() {
        if (!this.isShowing) {
            super.show()
        }
    }

    override fun dismiss() {
        if (this.isShowing) {
            super.dismiss()
        }
    }

    override fun cancel() {
        if (this.isShowing) {
            super.cancel()
        }
    }

    companion object {

        private var dialog: LoadingDialog? = null
        private val mainHandler = Handler(Looper.getMainLooper())

        fun showDialog(activity: Activity) {
            mainHandler.post {
                if (activity.isFinishing) {
                    return@post
                }
                if (dialog == null) {
                    dialog = LoadingDialog(activity)
                    dialog?.show()
                }
            }
        }

        fun dismissDialog() {
            mainHandler.post {
                dialog?.let {
                    if (it.isShowing) {
                        it.dismiss()
                        dialog = null
                    }
                }
            }
        }
    }
}