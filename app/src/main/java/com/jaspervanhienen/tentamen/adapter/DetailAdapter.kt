package com.jaspervanhienen.tentamen.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jaspervanhienen.tentamen.R
import com.jaspervanhienen.tentamen.model.PokemonDetail
import com.jaspervanhienen.tentamen.viewholder.DetailViewHolder
import kotlinx.android.synthetic.main.pokemon_detail_row.view.*

class DetailAdapter(private val pokemonDetail: PokemonDetail): RecyclerView.Adapter<DetailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val detailRow = inflater.inflate(R.layout.pokemon_detail_row, parent, false)

        return DetailViewHolder(
            detailRow
        )
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val headerText = when(position) {
            0 -> "base experience"
            1 -> "Height"
            2 -> "Id"
            else -> "not available"
        }

        val contentText = when(position) {
            0 -> pokemonDetail.getBaseExperience()
            1 -> pokemonDetail.getHeight()
            2 -> pokemonDetail.getId()
            else -> "not available"
        }

        holder.itemView.textView_header.text = headerText
        holder.itemView.textView_content.text = contentText.toString()
    }

}