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
import com.jaspervanhienen.tentamen.Model.PokemonDetail
import kotlinx.android.synthetic.main.pokemon_detail_row.view.*
import kotlinx.android.synthetic.main.pokemon_details.*
import kotlinx.android.synthetic.main.pokemon_details.view.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class DetailsActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_details)
        val url = getIntent().getStringExtra("URL") as String

        DoAsync {
            this.getPokemonDetails(url)
        }

    }

    private class DetailAdapter(private val pokemondetail: PokemonDetail): RecyclerView.Adapter<DetailViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val detailRow = inflater.inflate(R.layout.pokemon_detail_row, parent, false)

            Log.d("name is: ", pokemondetail.getName())

            return DetailViewHolder(detailRow)

        }

        override fun getItemCount(): Int {
            return pokemondetail.count
        }

        override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
            val headerText = when(position) {
                0 -> "Name"
                1 -> "base experience"
                2 -> "Height"
                3 -> "Id"
                4 -> "image"
                else -> "not available"
            }

            val contentText = when(position) {
                0 -> pokemondetail.getName()
                1 -> pokemondetail.getBaseExperience()
                2 -> pokemondetail.getHeight()
                3 -> pokemondetail.getId()
                else -> "not available"
            }
            holder.itemView.textView_header.text = headerText
            holder.itemView.textView_content.text = contentText.toString()
        }

    }

    private fun getPokemonDetails(url : String) {
        val queue = Volley.newRequestQueue(this)
        var pokemonResult : JSONObject
        Log.d("detail url", url)

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
        val name = pokemonResult.getString("name")
        val baseExperience = pokemonResult.getInt("base_experience")
        val height = pokemonResult.getInt("height")
        val id = pokemonResult.getInt("id")
        val sprites = pokemonResult.getJSONObject("sprites")

        pokemonDetail = PokemonDetail(name, baseExperience, height, id, sprites)
        this.setRecycler(pokemonDetail)
    }

    private fun setRecycler(pokemondetail: PokemonDetail) {
        recyclerView_details.layoutManager = LinearLayoutManager(this)
        recyclerView_details.adapter = DetailAdapter(pokemondetail)
    }

    private class DetailViewHolder(view: View): RecyclerView.ViewHolder(view) {}
}