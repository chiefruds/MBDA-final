package com.jaspervanhienen.tentamen

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jaspervanhienen.tentamen.Model.Pokemon
import kotlinx.android.synthetic.main.pokemon_row.view.*

class MainAdapter(private val pokemonlist : MutableList<Pokemon>): RecyclerView.Adapter<MainViewHolder>() {

    val pokemons = pokemonlist

    override fun getItemCount(): Int {
        return this.pokemons.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cell = layoutInflater.inflate(R.layout.pokemon_row, parent, false)

        return MainViewHolder(cell)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val url = pokemons[position].getUrl()
        holder.itemView.textView_pokemon_name.text = pokemons[position].getName()
        //holder.itemView.imageView.setImageURI(Uri.parse(url));
        holder.setUrl(url)
    }


}

class MainViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private var url = ""

    fun setUrl(url : String) {
        this.url = url
    }
    init {
        view.setOnClickListener {
            println("Test")

            val intent = Intent(view.context, DetailsActivity::class.java)
            intent.putExtra("URL", url)
            view.context.startActivity(intent)
        }
    }
}