package com.example.week7_getpokemon.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.week7_getpokemon.R
import com.example.week7_getpokemon.model.details.Stat

class StatsAdapter(val statsList : List<Stat>) : RecyclerView.Adapter<StatsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val statValue: TextView = itemView.findViewById(R.id.move_name)

        fun bind(stat: Stat) {
            val context = itemView.context
            statValue.text = stat.stat.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.moves_list, parent, false)
        val viewHolder = ViewHolder(itemView)
        viewHolder.itemView.background.setTint(getRandomColor())
        return viewHolder
    }

    private fun getRandomColor(): Int {
        val cardColors = arrayOf("#072AC8", "#EEC8E0", "#60D394")
        val randomIndex = (0 until cardColors.size).random()
        return Color.parseColor(cardColors[randomIndex])

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val statssss = statsList[position]
        holder.bind(statssss)
    }

    override fun getItemCount(): Int = statsList.size

}