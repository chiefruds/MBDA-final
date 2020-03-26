package com.jaspervanhienen.tentamen

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
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
        val pokemonlist = mutableListOf<Pokemon>()
        val pokemonArray: JSONArray = pokemonResult.getJSONArray("results")
        for (i in 0 until pokemonArray.length()) {
            try {
                val pokemon = pokemonArray.getJSONObject(i)
                // Pulling items from the array
                val name = pokemon.getString("name")
                val url = pokemon.getString("url")
                val newPokemon = Pokemon(name, url)
                pokemonlist.add(newPokemon)

            } catch (e: JSONException) {
                Log.e("error", e.message)
            }
        }
        this.setRecycler(pokemonlist)
    }

    //set the recycler view
    private fun setRecycler(pokemonlist: MutableList<Pokemon>) {
        recyclerView_main.layoutManager = LinearLayoutManager(this)
        recyclerView_main.adapter = MainAdapter(pokemonlist)
    }

}

