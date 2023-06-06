package com.example.week7_getpokemon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week7_getpokemon.R
import com.example.week7_getpokemon.model.Result

class PokemonAdapter(val pokemonList: List<Result>, val listener: OnItemClickListener): RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    private var filteredPokemonList: List<Result> = pokemonList

    interface OnItemClickListener {
        fun onItemClick(pokemon: Result)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pokemonImage: ImageView = itemView.findViewById(R.id.move_image)
        val pokemonName: TextView = itemView.findViewById(R.id.move_name)
        val pokemonNumber: TextView = itemView.findViewById(R.id.pokemonNumber)

        fun setSerialNumber(number: Int) {
            val formattedNumber = String.format("#%03d", number)
            pokemonNumber.text = formattedNumber

        }

        fun bind(pokemon: Result) {
            val context = itemView.context
            val url = pokemon.url
            val imageNo = url.split("https://pokeapi.co/api/v2/pokemon/")[1].split("/")[0]
            Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$imageNo.png")
                .into(pokemonImage)
            pokemonName.text = pokemon.name


            // Set the click listener for the item view
            itemView.setOnClickListener {
                listener.onItemClick(pokemon)
            }
        }
    }

    fun filter(query: String) {
        filteredPokemonList = if (query.isBlank()) {
            pokemonList
        } else {
            pokemonList.filter { pokemon ->
                pokemon.name.contains(query.trim(), ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = pokemonList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        val serialNumber = position + 1

        holder.setSerialNumber(serialNumber)
        holder.bind(pokemon)
    }

}
