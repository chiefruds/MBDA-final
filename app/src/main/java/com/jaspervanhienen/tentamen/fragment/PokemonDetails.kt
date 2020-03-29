package com.jaspervanhienen.tentamen.fragment

import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaspervanhienen.tentamen.DetailCallback
import com.jaspervanhienen.tentamen.DoAsync
import com.jaspervanhienen.tentamen.PokemonService
import com.jaspervanhienen.tentamen.R
import com.jaspervanhienen.tentamen.adapter.DetailAdapter
import com.jaspervanhienen.tentamen.model.PokemonDetail
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pokemon_row.view.*
import org.w3c.dom.Text

class PokemonDetails : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View? = inflater.inflate(R.layout.pokemon_details, container, false)
        if(view != null) {
            val pokemonService = PokemonService(this.activity)
            val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView_details)

            //retrieve url for pokemon
            var url = activity?.intent?.getStringExtra("URL")
            if(url == null) {
                url = ""
            }

            val name = view.findViewById(R.id.textView_name) as TextView
            val image = view.findViewById<ImageView>(R.id.imageView_details)

            //fetch all pokemon and start recycler view
            DoAsync {
                pokemonService.getPokemonDetails(url, object : DetailCallback {
                    override fun onSuccess(result: PokemonDetail) {
                        recyclerView.adapter = DetailAdapter(result)
                        recyclerView.layoutManager = LinearLayoutManager(context)
                        name.text = result.getName()
                        Picasso.get().load(result.getSprite("front_default")).into(image)
                    }
                })
            }
        }

        return view
    }
}