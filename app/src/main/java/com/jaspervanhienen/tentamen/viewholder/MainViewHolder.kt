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

//            val firstFragment = PokemonDetails()
//            firstFragment.arguments = intent.extras
//            val transaction = fragmentManager.beginTransaction()
//            transaction.add(R.id.LinearLayout1, firstFragment)
//            transaction.commit()

//            val fragment = PokemonDetails()
//            val activity = view.context as AppCompatActivity
//            activity.getSupportFragmentManager().beginTransaction()
//               .replace(R.id.container, fragment)
//                .commit();
      }
    }
}