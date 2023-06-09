package com.example.week7_getpokemon.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.week7_getpokemon.R
import com.example.week7_getpokemon.model.details.Ability

class AbilityAdapter (val abilityList: List<Ability>): RecyclerView.Adapter<AbilityAdapter.ViewHolder>() {
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val abilityName: TextView = itemView.findViewById(R.id.move_name)

        fun bind(ability: Ability) {
            val context = itemView.context
            abilityName.text = ability.ability.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.moves_list, parent, false)
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
        val abilityDisplay = abilityList[position]
        holder.bind(abilityDisplay)
    }

    override fun getItemCount(): Int = abilityList.size
}