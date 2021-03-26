package br.com.lucasfranco.common.tipealiases

import android.view.View
import br.com.lucasfranco.common.generics.MessageGenericObject

typealias GenericEvent = () -> Unit
typealias OnClickItem = (v: View) -> Unit
typealias OnErrorBaseViewModel = suspend (
    statusCode: Int?,
    error: MessageGenericObject,
    throwable: Throwable?
) -> MessageGenericObject
typealias OnSuccessBaseViewModel<T> = suspend (value: T) -> Unit
typealias OnCompletionBaseViewModel = suspend () -> Unit
