package com.example.week7_getpokemon.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.week7_getpokemon.R

class PokemonDetailsActivity : AppCompatActivity() {
     lateinit var pokeImage : ImageView
     lateinit var tvPokemon: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details)


        pokeImage = findViewById(R.id.pokeImage)
        tvPokemon = findViewById(R.id.tvPokemon)
        val bundle = intent.extras
//        val pokemonName = intent.getStringExtra("pokemonName")
//        val pokemonUrl = intent.getStringExtra("pokemonUrl")

      if (bundle  != null) {
          tvPokemon.text = bundle.getString("name")

          val url = bundle.getString("url")

          Glide.with(this)
              .load(url)
              .into(pokeImage)

        }

//        tvPokemon.text = pokemonName
//        Glide.with(this)
//            .load(pokemonUrl)
//            .into(pokeImage)



    }
}