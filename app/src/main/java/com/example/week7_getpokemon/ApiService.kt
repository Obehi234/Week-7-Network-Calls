package com.example.week7_getpokemon

import com.example.week7_getpokemon.model.PokemonResponse
import com.example.week7_getpokemon.model.details.PokemonDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("pokemon/")
    suspend fun getPokemon() : Response<PokemonResponse>

//moves, abilities, stats, images
    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(@Path("name") name: String) : Response<PokemonDetailsResponse>
}