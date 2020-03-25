package com.jaspervanhienen.tentamen

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jaspervanhienen.tentamen.Model.Pokemon
import kotlinx.android.synthetic.main.pokemon_row.view.*

class MainAdapter(private val pokemonlist : MutableList<Pokemon>): RecyclerView.Adapter<MainViewHolder>() {

    private val names = pokemonlist

    override fun getItemCount(): Int {
        return this.names.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cell = layoutInflater.inflate(R.layout.pokemon_row, parent, false)

        return MainViewHolder(cell)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.itemView.textView_pokemon_name.text = names[position].getName()
    }


}

class MainViewHolder(view: View): RecyclerView.ViewHolder(view) {
    init {
        view.setOnClickListener {
            println("Test")

            val intent = Intent(view.context, DetailsActivity::class.java)
            view.context.startActivity(intent)
        }
    }
}