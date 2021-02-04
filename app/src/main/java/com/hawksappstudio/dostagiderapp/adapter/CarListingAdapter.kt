package com.hawksappstudio.dostagiderapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.hawksappstudio.dostagiderapp.R

class CarListingAdapter(var carlist : ArrayList<String>, private val context: Context) : RecyclerView.Adapter<CarListingAdapter.CarViewHolder>() {


    class CarViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        var carItem = carlist[position]

        var title = holder.itemView.findViewById<TextView>(R.id.title)

        title.text = carItem

        holder.itemView.setOnClickListener {
            Navigation.findNavController(holder.itemView).navigate(R.id.action_listingFragment_to_detailFragment)
        }

    }

    override fun getItemCount(): Int {
        return  carlist.size
    }
}