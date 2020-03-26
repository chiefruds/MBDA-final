package com.jaspervanhienen.tentamen.viewholder

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jaspervanhienen.tentamen.activity.DetailsActivity

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