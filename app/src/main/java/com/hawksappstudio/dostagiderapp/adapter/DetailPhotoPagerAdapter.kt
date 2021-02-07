package com.hawksappstudio.dostagiderapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hawksappstudio.dostagiderapp.R
import com.hawksappstudio.dostagiderapp.databinding.ItemDetailPhotoBinding

class DetailPhotoPagerAdapter(private var images : ArrayList<String>) :   RecyclerView.Adapter<DetailPhotoPagerAdapter.DetailPhotoViewHolder>() {


    inner class DetailPhotoViewHolder(var view: ItemDetailPhotoBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(image:String?){
            if (image!=null){
                view.zoomImageView.visibility = View.VISIBLE
                view.detailPhotoProgress.visibility = View.GONE
                view.detailPhotoItem = image
            }else{
                view.errorText.visibility = View.VISIBLE
                view.zoomImageView.visibility = View.GONE
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailPhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view  = DataBindingUtil.inflate<ItemDetailPhotoBinding>(inflater,
            R.layout.item_detail_photo,parent,false)
        return  DetailPhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailPhotoViewHolder, position: Int) {
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