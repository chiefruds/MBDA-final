package com.jaspervanhienen.tentamen.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.jaspervanhienen.tentamen.*
import com.jaspervanhienen.tentamen.model.Pokemon
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    private var pokemonService = PokemonService(this);
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DoAsync {
            setRecycler()
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

    //set the recycler view
    private fun setRecycler() {
        var context = this
        this.pokemonService.getPokemon(object : VolleyCallback {
            override fun onSuccess(pokemonList: MutableList<Pokemon>) {
                recyclerView_main.layoutManager = LinearLayoutManager(context)
                recyclerView_main.adapter = MainAdapter(pokemonList)
            }
        })

    }

}

