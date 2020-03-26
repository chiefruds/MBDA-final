package com.jaspervanhienen.tentamen

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jaspervanhienen.tentamen.model.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pokemon_row.view.*

class MainAdapter(private val pokemonList : MutableList<Pokemon>): RecyclerView.Adapter<MainViewHolder>() {
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
        val image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + (position + 1) + ".png"
        holder.itemView.textView_pokemon_name.text = this.pokemonList[position].getName()
        Picasso.get().load(image).into(holder.itemView.imageView);
        holder.url = url

    }
}

class MainViewHolder(view: View): RecyclerView.ViewHolder(view) {
    var url = ""

    init {
        view.setOnClickListener {
            val intent = Intent(view.context, DetailsActivity::class.java)
            intent.putExtra("URL", url)
            view.context.startActivity(intent)
        }
    }
}