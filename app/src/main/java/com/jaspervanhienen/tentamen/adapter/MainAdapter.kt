package com.jaspervanhienen.tentamen.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.jaspervanhienen.tentamen.R
import com.jaspervanhienen.tentamen.model.Pokemon
import com.jaspervanhienen.tentamen.viewholder.MainViewHolder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pokemon_row.view.*
import java.util.*
import kotlin.collections.ArrayList

class MainAdapter(private var pokemonList : MutableList<Pokemon>): RecyclerView.Adapter<MainViewHolder>(), Filterable {
    private val pokemonListCopy: MutableList<Pokemon> = pokemonList.toMutableList()

    init {
        Log.d("Info", "Got here")
    }

    override fun getItemCount(): Int {
        return this.pokemonList.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cell = layoutInflater.inflate(R.layout.pokemon_row, parent, false)

        return MainViewHolder(cell)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val url = this.pokemonList[position].getUrl()
        holder.itemView.textView_pokemon_name.text = this.pokemonList[position].getName()
        Picasso.get().load(this.getPokemonImage(url)).into(holder.itemView.imageView)
        holder.url = url
    }

    private fun getPokemonImage(url: String) : String {
        var image = ""
        val regex = """(?<=/)[0-9]+""".toRegex()
        if(regex.find(url) != null) {
            val pokemonId = regex.find(url)!!.value
            image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$pokemonId.png"
        }
        return image
    }

    override fun getFilter(): Filter {
        return object: Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList: MutableList<Pokemon> = mutableListOf()

                if(constraint == null || constraint.isEmpty()) {
                    filteredList.addAll(pokemonListCopy)
                } else {
                    val pattern = constraint.toString().toLowerCase(Locale.ROOT).trim()

                    pokemonList.forEach {
                        if(it.getName().toLowerCase(Locale.ROOT).contains(pattern)) {
                            filteredList.add(it)
                        }
                    }
                }

                val results = FilterResults()
                results.values = filteredList

                return results
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                pokemonList.clear()
                pokemonList.addAll(results?.values as ArrayList<out Pokemon>)
                notifyDataSetChanged()
            }
        }
    }
}