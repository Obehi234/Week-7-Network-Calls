package com.example.week7_getpokemon.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week7_getpokemon.R
import com.example.week7_getpokemon.model.details.Move


class MovesAdapter(val moveList: List<Move>) : RecyclerView.Adapter<MovesAdapter.ViewHolder>()  {
    class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){
        val moveName: TextView = itemView.findViewById(R.id.move_name)

        fun bind(move: Move) {
            //val context = itemView.context
//            val url = move.move.url
//            Glide.with(context)
//                .load(url)
//                .into(moveImage)
            moveName.text = move.move.name
        }
//            val imageNo = url.split("https://pokeapi.co/api/v2/move/")[1].split("/")[0]
//            Glide.with(context)
//                .load("https://pokeapi.co/api/v2/move/")
//                .into(moveImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.moves_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val move = moveList[position]
        holder.bind(move)
    }

    override fun getItemCount(): Int = moveList.size
}