package br.com.lucasfranco.common.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.ViewDataBinding
import br.com.lucasfranco.common.extensions.associateViewModel
import br.com.lucasfranco.common.extensions.pop
import br.com.lucasfranco.common.viewmodel.BaseViewModel

abstract class BaseViewModelFragment<T : ViewDataBinding, VM : BaseViewModel> : BaseFragment() {

    abstract val bindingVariable: Int?

    abstract val getLayoutId: Int?

    abstract val viewModel: VM?

    var binding: T? = null

    override fun initialize() {
        activity?.onBackPressedDispatcher?.addCallback(this) {
            onBackPressed()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = associateViewModel(
            inflater,
            container,
            getLayoutId,
            viewModel,
            bindingVariable
        )
        return binding?.root ?: super.onCreateView(inflater, container, savedInstanceState)
    }

    open fun onBackPressed() {
        pop()
    }
}