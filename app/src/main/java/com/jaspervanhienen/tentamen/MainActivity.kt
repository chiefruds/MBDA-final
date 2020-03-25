package com.jaspervanhienen.tentamen

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.getPokemon()
    }

    private fun getPokemon() {
        val textView = findViewById<TextView>(R.id.pokemon)

        val queue = Volley.newRequestQueue(this)
        val url = "https://pokeapi.co/api/v2/pokemon"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                val pokemon = JSONObject(response)
                val singlePokemon = pokemon.getJSONArray("results").getJSONObject(0).getString("name")
                Log.d("pokemon", singlePokemon)
                textView.text = "Response is: " + singlePokemon
            },
            Response.ErrorListener { textView.text = "That didn't work!" })
        
        queue.add(stringRequest)

    }
}
