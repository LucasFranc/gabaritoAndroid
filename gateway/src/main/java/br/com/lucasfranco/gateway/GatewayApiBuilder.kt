package br.com.lucasfranco.gateway

import android.content.Context
import br.com.lucasfranco.gateway.interceptors.DeviceLogRequestInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

abstract class GatewayApiBuilder constructor(
    private val context: Context
) {

    fun getBaseRetrofit(
        customConfigBuilder: OkHttpClient.Builder.() -> OkHttpClient.Builder = { this }
    ): Retrofit =
        Retrofit.Builder().apply {
            client(makeBaseOkHttpClient(getUrl(), customConfigBuilder))
            baseUrl(getUrl().url)
            makeScaalars()
            makeConverterFactory()
        }.build()

    private fun Retrofit.Builder.makeConverterFactory() {
        addConverterFactory(GsonConverterFactory.create())
    }

    private fun Retrofit.Builder.makeScaalars() {
        if (enableScalarsConverterFactory()) {
            addConverterFactory(ScalarsConverterFactory.create())
        }
    }

    protected open fun makeBaseOkHttpClient(
        url: URLs,
        customConfigBuilder: OkHttpClient.Builder.() -> OkHttpClient.Builder = { this }
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .customConfigBuilder()
            .makeShowHttpLogging()
            .makeDeviceLogRequest()

        return builder.build()
    }

    private fun OkHttpClient.Builder.makeShowHttpLogging(): OkHttpClient.Builder {
        addInterceptor(
            HttpLoggingInterceptor().setLevel(
                if (BuildConfig.SHOW_HTTP_LOGGIN) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            )
        )
        connectTimeout(OKHTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(OKHTTP_READ_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(OKHTTP_WRITE_TIMEOUT, TimeUnit.SECONDS)
        return this
    }

    private fun OkHttpClient.Builder.makeDeviceLogRequest(): OkHttpClient.Builder {
        addInterceptor(DeviceLogRequestInterceptor())
        return this
    }

    abstract fun getUrl(): URLs

    open fun isEncrypt(): Boolean = false

    open fun enableScalarsConverterFactory(): Boolean = false

    companion object {
        const val OKHTTP_CONNECT_TIMEOUT = 60L
        const val OKHTTP_READ_TIMEOUT = 60L
        const val OKHTTP_WRITE_TIMEOUT = 60L
    }
}