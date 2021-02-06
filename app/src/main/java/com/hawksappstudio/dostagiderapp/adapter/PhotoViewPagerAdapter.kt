package com.hawksappstudio.dostagiderapp.adapter

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hawksappstudio.dostagiderapp.R
import com.hawksappstudio.dostagiderapp.databinding.ItemPhotoBinding

import com.hawksappstudio.dostagiderapp.utils.downloadBigUrl
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotoViewPagerAdapter(private var images : ArrayList<String>) :
    RecyclerView.Adapter<PhotoViewPagerAdapter.PhotoViewHolder>() {


   inner class PhotoViewHolder(var view: ItemPhotoBinding) : RecyclerView.ViewHolder(view.root) {
       fun bind(image:String?){
           view.photoItem = image
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view  = DataBindingUtil.inflate<ItemPhotoBinding>(inflater,R.layout.item_photo,parent,false)
        return  PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int {
            return images.size
    }

    fun addList(newImageList : List<String>){
        images.clear()
        images.addAll(newImageList)
        notifyDataSetChanged()
    }
}