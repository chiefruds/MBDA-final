package com.jaspervanhienen.tentamen.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jaspervanhienen.tentamen.R
import com.jaspervanhienen.tentamen.fragment.PokemonDetails
import com.jaspervanhienen.tentamen.fragment.PokemonList
import com.jaspervanhienen.tentamen.model.PokemonDetail

class DetailsActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        Log.d("real url: ", )

        if(savedInstanceState == null) {
            val activity = this
            val fragment = PokemonDetails()
            activity.getSupportFragmentManager().beginTransaction()
                .add(R.id.details, fragment)
                .commit();
        }
    }
}