package com.example.week7_getpokemon.activities

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week7_getpokemon.ApiService
import com.example.week7_getpokemon.R
import com.example.week7_getpokemon.RetrofitHelper
import com.example.week7_getpokemon.adapter.AbilityAdapter
import com.example.week7_getpokemon.adapter.MovesAdapter
import com.example.week7_getpokemon.adapter.StatsAdapter
import kotlinx.coroutines.launch

class PokemonDetailsActivity : AppCompatActivity() {
    lateinit var pokeImage: ImageView
    lateinit var tvPokemon: TextView
    private var progressDialog: ProgressDialog? = null
    private lateinit var apiService: ApiService
    var pokemonName: String? = ""
    private lateinit var rvMoves: RecyclerView
    private lateinit var rvStats: RecyclerView
    private lateinit var rvAbilities: RecyclerView
    private lateinit var adapter: MovesAdapter
    private lateinit var statsAdapter: StatsAdapter
    private lateinit var abilityAdapter: AbilityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details)

        apiService = RetrofitHelper.getInstance().create(ApiService::class.java)

        pokeImage = findViewById(R.id.pokeImage)
        tvPokemon = findViewById(R.id.tvPokemon)
        rvMoves = findViewById(R.id.rvMoves)
        rvStats = findViewById(R.id.rvStats)
        rvAbilities = findViewById(R.id.rvAbilities)
        val bundle = intent.extras

        if (bundle != null) {
            pokemonName = bundle.getString("name")
        }
        tvPokemon.text = pokemonName

        getPokemonDetails()
    }

    private fun getPokemonDetails() {
        lifecycleScope.launch {
            showLoading("Getting, please wait ...")

            val result = apiService.getPokemonDetails(pokemonName ?: "")
            adapter = MovesAdapter(result.body()?.moves ?: listOf())
            statsAdapter = StatsAdapter(result.body()?.stats ?: emptyList())
            abilityAdapter = AbilityAdapter(result.body()?.abilities?: emptyList())

            if (result.isSuccessful) {
                if (result.body() != null) {
                    //Get request success

                    val pokemonImage = result.body()!!.sprites.back_default
                    Glide.with(this@PokemonDetailsActivity)
                        .load(pokemonImage)
                        .into(pokeImage)

                    rvMoves.adapter = adapter
                    rvStats.adapter = statsAdapter
                    rvAbilities.adapter = abilityAdapter

                    Log.d("Get Moves", "getPokemonDetails ${result.body()}")

                } else {
                    //Request failed
                    Log.e("Get Moves", "getPokemonDetails failed :${result.message()}")
                }
                progressDialog?.dismiss()
            }
        }
    }

    private fun showLoading(message: String) {
        progressDialog = ProgressDialog.show(this, null, message, true)
    }


}