package com.jaspervanhienen.tentamen.viewholder

import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jaspervanhienen.tentamen.R
import com.jaspervanhienen.tentamen.activity.DetailsActivity
import com.jaspervanhienen.tentamen.fragment.PokemonDetails
import com.jaspervanhienen.tentamen.model.PokemonDetail

class MainViewHolder(view: View): RecyclerView.ViewHolder(view) {
    var url = ""

    init {
        view.setOnClickListener {
            val intent = Intent(view.context, DetailsActivity::class.java)
            intent.putExtra("URL", url)
            view.context.startActivity(intent)
      }
    }
}