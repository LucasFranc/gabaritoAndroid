package br.com.lucasfranco.common.extensions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import br.com.lucasfranco.common.dialogs.LoadingDialog
import br.com.lucasfranco.common.fragment.BaseFragment
import br.com.lucasfranco.common.viewmodel.BaseViewModel
import br.com.lucasfranco.util.castOrNull
import timber.log.Timber

fun <T : ViewDataBinding> BaseFragment.associateViewModel(
    layoutInflater: LayoutInflater,
    container: ViewGroup?,
    layoutId: Int? = null,
    viewModel: BaseViewModel? = null,
    bindingVariable: Int? = null,
): T? {
    val binding = layoutId?.let {
        DataBindingUtil.inflate<T>(layoutInflater, it, container, false)
    }
    viewModel?.message?.observe(
        viewLifecycleOwner,
        Observer { error ->
            error?.let { errorLet ->
                //showMessage(errorLet)
                viewModel.message.value = null
            }
        }
    )

    viewModel?.showLoading?.observe(
        viewLifecycleOwner,
        Observer {
            if (it == true) {
                getActivityForError()?.let { activityLet ->
                    LoadingDialog.showDialog(activityLet)
                } ?: run {
                    Timber.e("FragmentExt didn't show loading")
                }
            } else {
                LoadingDialog.dismissDialog()
            }
        }
    )

    binding?.lifecycleOwner = viewLifecycleOwner
    bindingVariable?.let { bindingVariableLet ->
        binding?.setVariable(bindingVariableLet, viewModel)
    }
    binding?.executePendingBindings()

    return binding
}

fun Fragment.navigateTo(direction: NavDirections) {
    try {
        findNavController().navigate(direction)
    } catch (e: IllegalArgumentException) {
        Timber.e(e)
    }
}

fun Fragment.navigateTo(direction: NavDirections, bundle: Bundle) {
    try {
        findNavController().navigate(direction.actionId, bundle)
    } catch (e: IllegalArgumentException) {
        Timber.e(e)
    }
}

fun Fragment.navigateTo(idDirection: Int, bundle: Bundle) {
    try {
        findNavController().navigate(idDirection, bundle)
    } catch (e: IllegalArgumentException) {
        Timber.e(e)
    }
}

fun Fragment.navigateWithTransition(
    direction: NavDirections,
    transitionExtras: FragmentNavigator.Extras
) {
    try {
        findNavController().navigate(direction, transitionExtras)
    } catch (e: IllegalArgumentException) {
        Timber.e(e)
    }
}

fun Fragment.pop() {
    try {
        findNavController().navigateUp()
    } catch (e: IllegalArgumentException) {
        Timber.e(e)
    }
}

fun Fragment.popBackStack() {
    try {
        findNavController().popBackStack()
    } catch (e: IllegalArgumentException) {
        Timber.e(e)
    }
}

fun Fragment.initHostFragment(fragmentList: HashMap<Int, Int>, bundle: Bundle? = null) {
    fragmentList.map { (key, value) -> initHostFragment(key, value, bundle) }
}

fun Fragment.initHostFragment(idNavHost: Int, idFragment: Int, bundle: Bundle? = null) {
    childFragmentManager
        .findFragmentById(idNavHost)
        ?.castOrNull<NavHostFragment>()
        ?.navController
        ?.navigate(idFragment, bundle) ?: Timber.e("Fragment not found $idFragment")
}

fun <T> Fragment.stateGetLiveData(key: String, initValue: T? = null): LiveData<T>? =
    findNavController()
        .currentBackStackEntry
        ?.savedStateHandle
        ?.run {
            if (initValue != null) {
                getLiveData(key, initValue)
            } else {
                getLiveData(key)
            }
        }

fun <T> Fragment.stateSetValue(key: String, value: T?) {
    findNavController()
        .currentBackStackEntry
        ?.savedStateHandle
        ?.set(key, value)
}

fun Fragment.hideKeyboard() {
    val imm: InputMethodManager? = activity?.let { activityLet ->
        ContextCompat.getSystemService(
            activityLet,
            InputMethodManager::class.java
        )
    }
    imm?.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
}