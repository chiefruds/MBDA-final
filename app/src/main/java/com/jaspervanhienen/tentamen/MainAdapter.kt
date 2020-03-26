package com.jaspervanhienen.tentamen

import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jaspervanhienen.tentamen.model.Pokemon
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
        holder.itemView.textView_pokemon_name.text = this.pokemonList[position].getName()
        //holder.itemView.imageView.setImageURI(Uri.parse(url));
        holder.url = url
        val image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        holder.itemView.imageView.setImageBitmap(bitmap);
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