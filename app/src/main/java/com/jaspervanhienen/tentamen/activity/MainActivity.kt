package com.jaspervanhienen.tentamen.activity

import com.jaspervanhienen.tentamen.adapter.MainAdapter
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jaspervanhienen.tentamen.*
import com.jaspervanhienen.tentamen.fragment.PokemonDetails
import com.jaspervanhienen.tentamen.fragment.PokemonList
import com.jaspervanhienen.tentamen.model.Pokemon
import kotlinx.android.synthetic.main.pokemon_list.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            val activity = this
            val fragment = PokemonList()
            activity.getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragment)
                .commit();
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)

        val searchItem = menu?.findItem(R.id.search_bar)
        val searchView = searchItem?.actionView as SearchView

        // Set listener for search bar
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                filter(query)
                return false
            }

            private fun filter(query: String?) {
                val adapter = recyclerView_main.adapter as MainAdapter
                adapter.filter.filter(query)
            }
        })

        return true
    }
}

