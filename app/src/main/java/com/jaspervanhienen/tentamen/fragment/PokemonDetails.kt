package com.jaspervanhienen.tentamen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.jaspervanhienen.tentamen.DetailCallback
import com.jaspervanhienen.tentamen.PokemonListCallback
import com.jaspervanhienen.tentamen.PokemonService
import com.jaspervanhienen.tentamen.R
import com.jaspervanhienen.tentamen.adapter.DetailAdapter
import com.jaspervanhienen.tentamen.model.Pokemon
import com.jaspervanhienen.tentamen.model.PokemonDetail

class PokemonDetails: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View? = inflater.inflate(R.layout.pokemon_details, container, false)

        if(view != null) {
            val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView_details)
            val nameView = view.findViewById<TextView>(R.id.textView_pokemon_name)
            val pokemonService = PokemonService(this.activity)

            activity?.intent?.getStringExtra("URL")?.let {
                pokemonService.getPokemonDetails(it, object : DetailCallback {
                    override fun onSuccess(result: PokemonDetail) {
                        recyclerView.adapter = DetailAdapter(result)
                        nameView.text = result.getName()
                    }
                })
            }
        }

        return view
    }
}