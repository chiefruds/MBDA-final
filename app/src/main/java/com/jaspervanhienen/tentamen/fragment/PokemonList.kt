package com.jaspervanhienen.tentamen.fragment

import android.app.Activity
import com.jaspervanhienen.tentamen.adapter.MainAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaspervanhienen.tentamen.PokemonService
import com.jaspervanhienen.tentamen.R
import com.jaspervanhienen.tentamen.VolleyCallback
import com.jaspervanhienen.tentamen.model.Pokemon
import kotlinx.android.synthetic.main.activity_main.*

class PokemonList(): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View? = inflater.inflate(R.layout.pokemon_list, container, false)

        if(view != null) {
            val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView_main)
            val pokemonService = PokemonService(this.getActivity())

            pokemonService.getPokemon(object : VolleyCallback {
                override fun onSuccess(result: MutableList<Pokemon>) {
                    recyclerView.adapter = MainAdapter(result)
                }
            })
        }

        return view
    }
}