package br.com.lucasfranco.gateway.interceptors

import br.com.lucasfranco.gateway.BuildConfig
import br.com.lucasfranco.util.cloneResponse
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber

class DeviceLogRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        log(response.newBuilder().build(), request.newBuilder().build())

        return response.newBuilder().build()
    }

    private fun log(response: Response, request: Request) {
        if (!response.isSuccessful && !BuildConfig.DEBUG) {
            Timber.e(
                "Error in request code = %d endpoint = %s method = %s body = %s",
                response.code,
                request.url.encodedPath,
                request.method,
                response.body?.cloneResponse()?.string().toString()
            )
        }
    }
}