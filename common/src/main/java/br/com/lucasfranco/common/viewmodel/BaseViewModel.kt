package br.com.lucasfranco.common.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import br.com.lucasfranco.common.generics.ErrorNetwork
import br.com.lucasfranco.common.generics.MessageGenericObject
import br.com.lucasfranco.common.generics.ErrorObject
import br.com.lucasfranco.common.tipealiases.OnClickItem
import br.com.lucasfranco.common.tipealiases.OnCompletionBaseViewModel
import br.com.lucasfranco.common.tipealiases.OnErrorBaseViewModel
import br.com.lucasfranco.common.tipealiases.OnSuccessBaseViewModel
import br.com.lucasfranco.domain.ResultWrapper
import br.com.lucasfranco.domain.Error
import br.com.lucasfranco.util.AbstractMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Main + job

    val message = MutableLiveData<MessageGenericObject>()

    val showLoading = MutableLiveData<Boolean>().apply {
        value = false
    }

    var onClickItem: OnClickItem? = null

    open fun initializer(onClickItem: OnClickItem) {
        this.onClickItem = onClickItem
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    open fun onItemClicked(v: View) {
        onClickItem?.invoke(v)
    }

    /**
     * this method allows to change the way the usecase is
     * executed, it is possible to get instant loads and error
     * messages handled or customized when passing a usecase
     *
     * @param onError allows you to customize the error message
     * @param showLoadingFlag This parameter allows you to decide whether to
     * show a load or not (Optional)
     */
    fun <T : Any> Flow<ResultWrapper<T>>.exec(
        onError: OnErrorBaseViewModel = { _,err, _ -> err },
        showLoadingFlag: Boolean = true
    ): LiveData<T> = mapNotNull {
        when (val result = it) {
            is ResultWrapper.Loading -> {
                showLoadingWithFlag(showLoadingFlag)
                null
            }
            is ResultWrapper.Failure -> {
                resolveResultWrapperFailure(result, onError)
                null
            }
            is ResultWrapper.Success<T> -> result.value
            is ResultWrapper.DismissLoading -> {
                hideLoadingWithFlag(showLoadingFlag)
                null
            }
        }
    }.catch {
        hideLoadingWithFlag(showLoadingFlag)
        Timber.e(it)
    }.asLiveData(coroutineContext)

    /**
     * this method allows you to use an AbstractUseCase to
     * return a simple value, without needing LiveData
     *
     * @param onError allows you to customize the error message (Optional)
     * @param onSuccessBaseViewModel Allows you to use your return from
     * an endpoint or a database, in your view (Optional)
     * @param onCompletionBaseViewModel It is equivalent to the try/catch finally (Optional)
     * @param showLoadingFlag This parameter allows you to decide whether to
     * show a load or not (Optional)
     */
    @ExperimentalCoroutinesApi
    open fun <T : Any> Flow<ResultWrapper<T>>.singleExec(
        onError: OnErrorBaseViewModel = { _, err, _ -> err },
        onSuccessBaseViewModel: OnSuccessBaseViewModel<T>? = null,
        onCompletionBaseViewModel: OnCompletionBaseViewModel? = null,
        showLoadingFlag: Boolean = true
    ) = onEach {
        when (val result = it) {
            is ResultWrapper.Loading -> {
                showLoadingWithFlag(showLoadingFlag)
            }
            is ResultWrapper.Failure -> {
                resolveResultWrapperFailure(result, onError)
            }
            is ResultWrapper.Success<T> ->
                onSuccessBaseViewModel?.invoke(result.value)
            is ResultWrapper.DismissLoading -> {
                hideLoadingWithFlag(showLoadingFlag)
            }
        }
    }.onCompletion {
        if (it?.cause != null) {
            Timber.e(it)
        }
        onCompletionBaseViewModel?.invoke()
    }.catch {
        hideLoadingWithFlag(showLoadingFlag)
        Timber.e(it)
    }.launchIn(CoroutineScope(coroutineContext))

    fun <PARAMETER, RESULT> LiveData<PARAMETER>.map(abstractMap: AbstractMapper<PARAMETER, RESULT>): LiveData<RESULT> {
        return Transformations.map(this, abstractMap::map)
    }

    private suspend fun resolveResultWrapperFailure(
        result: ResultWrapper.Failure,
        onError: OnErrorBaseViewModel
    ) {
        val errorResolved = when (val error = result.error) {
            is Error.Server -> makeOnError(
                statusCode = error.code,
                error = ErrorObject().apply {
                    messageString = error.message
                },
                throwable = error.cause,
                onError = onError
            )
            is Error.Business -> makeOnError(
                error = ErrorObject().apply {
                    messageString = error.message
                },
                throwable = error.cause,
                onError = onError
            )
            is Error.UnknownException -> makeOnError(
                throwable = error.cause,
                onError = onError
            )
            is Error.NetworkException -> makeOnError(
                error = ErrorNetwork(error.cause),
                throwable = error.cause,
                onError = onError
            )
        }
        message.value = errorResolved
    }

    private suspend fun makeOnError(
        statusCode: Int? = null,
        error: MessageGenericObject? = null,
        throwable: Throwable? = null,
        onError: OnErrorBaseViewModel
    ): MessageGenericObject =
        onError(
            statusCode,
            error ?: ErrorObject(throwable),
            throwable
        )

    private fun hideLoadingWithFlag(showLoadingFlag: Boolean) {
        if (showLoadingFlag) {
            hideLoading()
        }
    }

    private fun showLoadingWithFlag(showLoadingFlag: Boolean) {
        if (showLoadingFlag) {
            showLoading()
        }
    }

    open fun hideLoading() {
        showLoading.value = false
    }

    open fun showLoading() {
        showLoading.value = true
    }
}