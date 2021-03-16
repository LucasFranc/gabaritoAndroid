package br.com.lucasfranco.gateway.endpoint

import br.com.lucasfranco.model.PokemonList
import retrofit2.http.GET

interface PokeEndpoints {

    @GET("pokemon?limit=151")
    suspend fun getAllOriginalPokemons(): PokemonList

}