package com.jaspervanhienen.tentamen.fragment

import MainAdapter
import android.os.Bundle
import com.jaspervanhienen.tentamen.viewholder.DetailViewHolder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.jaspervanhienen.tentamen.R
import com.jaspervanhienen.tentamen.model.PokemonDetail
import kotlinx.android.synthetic.main.pokemon_detail_row.view.*

class PokemonList(): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View? = inflater.inflate(R.layout.pokemon_list, container, false)

        if(view != null) {
            val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView_main)
            recyclerView.adapter = MainAdapter()
        }

        return view
    }
}