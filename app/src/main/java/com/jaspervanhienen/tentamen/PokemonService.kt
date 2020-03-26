package com.jaspervanhienen.tentamen

import android.app.Activity
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class PokemonService {
     fun getPokemon(context : AppCompatActivity) {
        //val textView = findViewById<TextView>(R.id.pokemon)

        val queue = Volley.newRequestQueue( context)
        val url = "https://pokeapi.co/api/v2/pokemon"
        var pokemon : JSONObject

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                pokemon = JSONObject(response)
                val singlePokemon = pokemon.getJSONArray("results").getJSONObject(0).getString("name")
                Log.d("pokemon", singlePokemon)
                //textView.text = "Response is: " + singlePokemon

            },
            Response.ErrorListener { Log.e("api error","That didn't work!") })

        queue.add(stringRequest)

    }

    fun getPokemonDetail(context : Activity, url : String) {
        val queue = Volley.newRequestQueue( context)
        val url = "https://pokeapi.co/api/v2/pokemon"
        var pokemon : JSONObject

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                pokemon = JSONObject(response)
                val singlePokemon = pokemon.getJSONArray("results").getJSONObject(0).getString("name")
                Log.d("pokemon", singlePokemon)
                //textView.text = "Response is: " + singlePokemon

            },
            Response.ErrorListener { Log.e("api error","That didn't work!") })

        queue.add(stringRequest)
    }
}