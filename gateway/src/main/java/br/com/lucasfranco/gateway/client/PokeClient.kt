package br.com.lucasfranco.gateway.client

import android.content.Context
import br.com.lucasfranco.gateway.GatewayApiBuilder
import br.com.lucasfranco.gateway.URLs
import br.com.lucasfranco.gateway.endpoint.PokeEndpoints
import br.com.lucasfranco.gateway.getUrlByBuildType

class PokeClient : GatewayApiBuilder() {
    override fun getUrl(): URLs = getUrlByBuildType()

    fun getPokeEndpoint(): PokeEndpoints = getBaseRetrofit().create(PokeEndpoints::class.java)
}