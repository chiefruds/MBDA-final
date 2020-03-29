package com.jaspervanhienen.tentamen.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jaspervanhienen.tentamen.R
import com.jaspervanhienen.tentamen.fragment.PokemonDetails

class DetailsActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_details)
    }
}