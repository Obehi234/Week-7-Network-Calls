package com.example.week7_getpokemon.model

data class PokemonResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Result>
)