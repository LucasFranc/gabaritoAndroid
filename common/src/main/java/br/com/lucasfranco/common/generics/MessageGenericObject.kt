package br.com.lucasfranco.common.generics

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import br.com.lucasfranco.common.R
import br.com.lucasfranco.common.tipealiases.GenericEvent
import timber.log.Timber

abstract class MessageGenericObject {
    abstract var type: TypeMessage

    @DrawableRes
    open var icon: Int = R.drawable.ic_error

    open var title: Int = R.string.generic_error_message_title

    open var titleString: String? = null

    open var message: Int = R.string.generic_error_message_message

    open var messageString: String? = null

    open var buttonText: Int = R.string.ok

    open var buttonTextString: String? = null

}

open class ErrorObject constructor(
    ex: Throwable? = null
) : MessageGenericObject() {

    init {
        Timber.d(ex)
    }

    override var type: TypeMessage = TypeMessage.ERROR
}

data class ErrorNetwork constructor(
    val ex: Throwable? = null
) : ErrorObject(ex) {

    init {
        Timber.d(ex)
    }

    override var type: TypeMessage = TypeMessage.ERROR
}

//Aqui poderia ter diferentes tipos de mensagens, para abrir dialogs diferentes,
//mas no caso, deixarei mapeado só a de erro
enum class TypeMessage {
    ERROR,
    SUCCESS,
    CHOOSE
}

fun makeErrorObject(ex: Exception? = null, onErrorObjectCreate: ErrorObject.() -> Unit): ErrorObject =
    ErrorObject(ex).apply(onErrorObjectCreate)