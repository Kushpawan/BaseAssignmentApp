package com.example.baseassignmentapp.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.baseassignmentapp.R
import com.example.baseassignmentapp.models.Drinks
import java.util.*

class SearchRecyclerAdapter(
    var context: Context,
    var drinkList: MutableList<Drinks?>
) : RecyclerView.Adapter<SearchRecyclerAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.search_item, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.searchAddress.text = drinkList[position]?.strDrink
        holder.image.load(drinkList[position]?.strDrinkThumb)
    }

    override fun getItemCount(): Int {
        return drinkList.size
    }

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var searchAddress: TextView = itemView.findViewById(R.id.drink_name_text)
        var image: ImageView = itemView.findViewById(R.id.drink_thumb)

    }
}