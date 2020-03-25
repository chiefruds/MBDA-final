package com.jaspervanhienen.tentamen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.pokemon_row.view.*

class MainAdapter: RecyclerView.Adapter<MainViewHolder>() {

    private val names = listOf("pikachu", "turtwig", "blastoise", "afro")

    override fun getItemCount(): Int {
        return this.names.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cell = layoutInflater.inflate(R.layout.pokemon_row, parent, false)

        return MainViewHolder(cell)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.itemView.textView_pokemon_name.text = names[position]
    }


}

class MainViewHolder(view: View): RecyclerView.ViewHolder(view) {

}