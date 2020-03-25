package com.jaspervanhienen.tentamen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.jaspervanhienen.tentamen.Model.Pokemon
import kotlinx.android.synthetic.main.pokemon_details.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class DetailsActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_details)

        recyclerView_details.layoutManager = LinearLayoutManager(this)
        recyclerView_details.adapter = DetailAdapter()
    }

    private class DetailAdapter: RecyclerView.Adapter<DetailViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val detailRow = inflater.inflate(R.layout.pokemon_detail_row, parent, false)

            return DetailViewHolder(detailRow)
        }

        override fun getItemCount(): Int {
            return 4
        }

        override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {}

    }

    private fun getPokemonDetails() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://pokeapi.co/api/v2/pokemon"
        var pokemonResult : JSONObject

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                pokemonResult = JSONObject(response)
                this.generateDetails(pokemonResult)
            },
            Response.ErrorListener { Log.e("api error","That didn't work!") })

        queue.add(stringRequest)
    }

    private fun generateDetails(pokemonResult : JSONObject) {
        val pokemonDetail : PokemonDetail
        val pokemonArray: JSONArray = pokemonResult.getJSONArray("results")
        for (i in 0 until pokemonArray.length()) {
            try {
                val pokemon = pokemonArray.getJSONObject(i)
                // Pulling items from the array
                val name = pokemon.getString("name")
                val url = pokemon.getString("url")
                val newPokemon = Pokemon(name, url)
                Log.d("pokemon", pokemon.getString("name"))
                pokemonlist.add(newPokemon)

            } catch (e: JSONException) {
                Log.e("error", e.message)
            }
        }
        Log.d("pokeList: ", "c: " + pokemonlist[6].getName())
        this.setRecycler(pokemonlist)
    }

    private class DetailViewHolder(view: View): RecyclerView.ViewHolder(view) {}
}