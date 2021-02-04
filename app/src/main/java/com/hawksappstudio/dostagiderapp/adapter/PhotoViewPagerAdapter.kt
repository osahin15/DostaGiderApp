package com.hawksappstudio.dostagiderapp.adapter

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.hawksappstudio.dostagiderapp.R
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotoViewPagerAdapter(private var images : List<Int>) :
    RecyclerView.Adapter<PhotoViewPagerAdapter.PhotoViewHolder>() {


   inner class PhotoViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val itemImage : ImageView = itemView.findViewById(R.id.photoImageView)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_photo,parent,false)
        return  PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {

        holder.itemImage.setImageResource(images[position])
    }

    override fun getItemCount(): Int {
            return images.size
    }
}