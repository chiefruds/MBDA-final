package com.jaspervanhienen.tentamen.activity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaspervanhienen.tentamen.DetailCallback
import com.jaspervanhienen.tentamen.DoAsync
import com.jaspervanhienen.tentamen.PokemonService
import com.jaspervanhienen.tentamen.R
import com.jaspervanhienen.tentamen.model.PokemonDetail
import kotlinx.android.synthetic.main.pokemon_detail_row.view.*
import kotlinx.android.synthetic.main.pokemon_details.*

class DetailsActivity: AppCompatActivity() {
    private var pokemonService = PokemonService(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_details)
    }
}