package com.jaspervanhienen.tentamen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.pokemon_details.*

class DetailsActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_details)

        recyclerView_details.layoutManager = LinearLayoutManager(this)
        recyclerView_details.adapter = DetailAdapter()
    }

    private class DetailAdapter: RecyclerView.Adapter<DetailViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val detailRow = inflater.inflate(R.layout.pokemon_detail_row, parent, false)

            return DetailViewHolder(detailRow)
        }

        override fun getItemCount(): Int {
            return 4
        }

        override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {}

    }

    private class DetailViewHolder(view: View): RecyclerView.ViewHolder(view) {}
}