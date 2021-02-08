package com.hawksappstudio.dostagiderapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.hawksappstudio.dostagiderapp.R
import com.hawksappstudio.dostagiderapp.databinding.ItemPhotoBinding
import com.hawksappstudio.dostagiderapp.utils.ItemClickListener

class PhotoViewPagerAdapter(private var images : ArrayList<String>) :
    RecyclerView.Adapter<PhotoViewPagerAdapter.PhotoViewHolder>(),ItemClickListener {


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
        holder.view.listenerPhoto = this

    }

    override fun getItemCount(): Int {
            return images.size
    }

    fun addList(newImageList : List<String>){
        images.clear()
        images.addAll(newImageList)
        notifyDataSetChanged()
    }

    override fun itemClickListener(view: View) {
        val bundle = bundleOf("images" to images)
        Navigation.findNavController(view).navigate(R.id.action_detailFragment_to_photoDetailFragment,bundle)
    }
}