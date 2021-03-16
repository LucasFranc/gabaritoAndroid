package br.com.lucasfranco.util

import okhttp3.ResponseBody
import retrofit2.Response

fun Response<*>.cloneErrorResponseBody(): ResponseBody? =
    errorBody()?.cloneResponse()

fun ResponseBody.cloneResponse(): ResponseBody? =
    safeLet(source().buffer?.clone(), contentLength()) { bufferClone, lenght ->
        ResponseBody.create(contentType(), lenght, bufferClone)
    }