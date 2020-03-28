package com.jaspervanhienen.tentamen.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.jaspervanhienen.tentamen.DoAsync
import com.jaspervanhienen.tentamen.R
import com.jaspervanhienen.tentamen.adapter.DetailAdapter
import com.jaspervanhienen.tentamen.model.PokemonDetail
import kotlinx.android.synthetic.main.pokemon_details.*
import org.json.JSONObject

class DetailsActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_details)
        val url = intent.getStringExtra("URL") as String

        DoAsync {
            this.getPokemonDetails(url)
        }
    }

    private fun getPokemonDetails(url : String) {
        val queue = Volley.newRequestQueue(this)
        var pokemonResult : JSONObject
        Log.d("detail url", url)

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener { response ->
                pokemonResult = JSONObject(response)
                this.generateDetails(pokemonResult)
            },
            Response.ErrorListener { Log.e("api error","That didn't work!") })

        queue.add(stringRequest)

    }

    private fun generateDetails(pokemonResult : JSONObject) {
        this.setRecycler(
            PokemonDetail(
                pokemonResult.getString("name"),
                pokemonResult.getInt("base_experience"),
                pokemonResult.getInt("height"),
                pokemonResult.getInt("id"),
                pokemonResult.getJSONObject("sprites")
            )
        )
    }

    private fun setRecycler(pokemonDetail: PokemonDetail) {
        recyclerView_details.layoutManager = LinearLayoutManager(this)
        recyclerView_details.adapter = DetailAdapter(pokemonDetail)
    }
}