package com.jaspervanhienen.tentamen.activity

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
import com.jaspervanhienen.tentamen.DoAsync
import com.jaspervanhienen.tentamen.R
import com.jaspervanhienen.tentamen.model.PokemonDetail
import kotlinx.android.synthetic.main.pokemon_detail_row.view.*
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

    private class DetailAdapter(private val pokemonDetail: PokemonDetail): RecyclerView.Adapter<DetailViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val detailRow = inflater.inflate(R.layout.pokemon_detail_row, parent, false)

            Log.d("name is: ", pokemonDetail.getName())

            return DetailViewHolder(
                detailRow
            )
        }

        override fun getItemCount(): Int {
            return pokemonDetail.count
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
                0 -> pokemonDetail.getName()
                1 -> pokemonDetail.getBaseExperience()
                2 -> pokemonDetail.getHeight()
                3 -> pokemonDetail.getId()
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
        recyclerView_details.adapter =
            DetailAdapter(
                pokemonDetail
            )
    }

    private class DetailViewHolder(view: View): RecyclerView.ViewHolder(view) {}
}