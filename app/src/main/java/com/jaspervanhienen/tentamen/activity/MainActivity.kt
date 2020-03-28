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
import com.jaspervanhienen.tentamen.DoAsync
import com.jaspervanhienen.tentamen.MainAdapter
import com.jaspervanhienen.tentamen.R
import com.jaspervanhienen.tentamen.model.Pokemon
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DoAsync {
            getPokemon()
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

    //fetch pokemon from api
    private fun getPokemon() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://pokeapi.co/api/v2/pokemon"
        var pokemonResult : JSONObject

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                pokemonResult = JSONObject(response)
                this.generatePokemon(pokemonResult)
            },
            Response.ErrorListener { Log.e("api error","That didn't work!") })

        queue.add(stringRequest)
    }

    //loop over pokemon JSON and add pokemon objects to
    private fun generatePokemon(pokemonResult: JSONObject) {
        val pokemonList = mutableListOf<Pokemon>()
        val pokemonArray: JSONArray = pokemonResult.getJSONArray("results")
        for (i in 0 until pokemonArray.length()) {
            try {
                val pokemon = pokemonArray.getJSONObject(i)
                // Pulling items from the array
                val name = pokemon.getString("name")
                val url = pokemon.getString("url")
                val newPokemon = Pokemon(name, url)
                pokemonList.add(newPokemon)

            } catch (e: JSONException) {
                Log.e("error", e.message)
            }
        }
        this.setRecycler(pokemonList)
    }

    //set the recycler view
    private fun setRecycler(pokemonList: MutableList<Pokemon>) {
        recyclerView_main.layoutManager = LinearLayoutManager(this)
        recyclerView_main.adapter =
            MainAdapter(pokemonList)
    }

}

