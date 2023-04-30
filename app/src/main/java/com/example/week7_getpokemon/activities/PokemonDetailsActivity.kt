package com.example.week7_getpokemon.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.week7_getpokemon.R

class PokemonDetailsActivity : AppCompatActivity() {
    private lateinit var pokeImage : ImageView
    private lateinit var tvPokemon: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details)


        val pokemonName = intent.getStringExtra("pokemonName")
        val pokemonUrl = intent.getStringExtra("pokemonUrl")

        pokeImage = findViewById(R.id.pokeImage)
        tvPokemon = findViewById(R.id.tvPokemon)

        tvPokemon.text = pokemonName
        Glide.with(this)
            .load(pokemonUrl)
            .into(pokeImage)



    }
}