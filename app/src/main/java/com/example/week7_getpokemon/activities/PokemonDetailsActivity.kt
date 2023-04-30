package com.example.week7_getpokemon.activities

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.week7_getpokemon.ApiService
import com.example.week7_getpokemon.R
import com.example.week7_getpokemon.RetrofitHelper
import kotlinx.coroutines.launch

class PokemonDetailsActivity : AppCompatActivity() {
     lateinit var pokeImage : ImageView
     lateinit var tvPokemon: TextView
     lateinit var tvDisplay: TextView
    private var progressDialog: ProgressDialog? = null
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details)

        apiService = RetrofitHelper.getInstance().create(ApiService::class.java)


        pokeImage = findViewById(R.id.pokeImage)
        tvPokemon = findViewById(R.id.tvPokemon)
        tvDisplay = findViewById(R.id.tvDisplay)
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
        getPokemonDetails()

//        tvPokemon.text = pokemonName
//        Glide.with(this)
//            .load(pokemonUrl)
//            .into(pokeImage)

    }

    private fun getPokemonDetails() {
        lifecycleScope.launch {
            showLoading("Getting, please wait ...")

            val result = apiService.getPokemonDetails("weedle")
            if(result.isSuccessful) {
                if (result.body() != null) {
                    //Get request success
                    Log.d("oooooo", "getPokemonDetails ${result.body()}")


                } else {
                    //Request failed
                    Log.e("oooooo", "getPokemonDetails failed :${result.message()}")
                }
                progressDialog?.dismiss()
            }
        }
    }

    private fun showLoading(message: String) {
        progressDialog = ProgressDialog.show(this, null, message, true)

    }


}