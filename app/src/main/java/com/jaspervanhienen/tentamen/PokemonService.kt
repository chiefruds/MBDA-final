package com.jaspervanhienen.tentamen

import android.app.Activity
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.jaspervanhienen.tentamen.model.Pokemon
import com.jaspervanhienen.tentamen.model.PokemonDetail
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class PokemonService//fetch pokemon from api
    (private var context: FragmentActivity?) {

    fun getPokemon(callback : PokemonListCallback) {
        val queue = Volley.newRequestQueue(this.context)
        val url = "https://pokeapi.co/api/v2/pokemon"
        var pokemonResult : MutableList<Pokemon>
        Log.d("api", "start")

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                pokemonResult = this.generatePokemon(JSONObject(response))
                callback.onSuccess(pokemonResult)
            },
            Response.ErrorListener { Log.e("api error","That didn't work!")})

        queue.add(stringRequest)
    }

    //loop over pokemon JSON and add pokemon objects to
    private fun generatePokemon(pokemonResult: JSONObject): MutableList<Pokemon> {
        val pokemonList = mutableListOf<Pokemon>()
        Log.d("generate", "pre start")
        val pokemonArray: JSONArray = pokemonResult.getJSONArray("results")
        Log.d("generate", "start")
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
        return pokemonList
    }

    //details
    fun getPokemonDetails(url : String, callback: DetailCallback) {
        val queue = Volley.newRequestQueue(this.context)
        var pokemonResult : JSONObject
        Log.d("detail url", url)

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener { response ->
                pokemonResult = JSONObject(response)
                callback.onSuccess(this.generateDetails(pokemonResult))
            },
            Response.ErrorListener { Log.e("api error","That didn't work!") })

        queue.add(stringRequest)

    }

    private fun generateDetails(pokemonResult : JSONObject) : PokemonDetail{

         return PokemonDetail(
                pokemonResult.getString("name"),
                pokemonResult.getInt("base_experience"),
                pokemonResult.getInt("height"),
                pokemonResult.getInt("id"),
                pokemonResult.getJSONObject("sprites")
            )
    }
}