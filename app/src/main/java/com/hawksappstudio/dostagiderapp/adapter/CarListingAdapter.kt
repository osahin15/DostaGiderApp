package com.hawksappstudio.dostagiderapp.adapter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hawksappstudio.dostagiderapp.R
import com.hawksappstudio.dostagiderapp.databinding.ItemListBinding
import com.hawksappstudio.dostagiderapp.model.ListModel
import com.hawksappstudio.dostagiderapp.model.ListModel.ListEntity
import com.hawksappstudio.dostagiderapp.utils.ListItemClickListener
import kotlinx.android.synthetic.main.item_list.view.*

class CarListingAdapter : PagedListAdapter<ListEntity,CarListingAdapter.CarItemViewHolder>(
    diffUtilCallBack),ListItemClickListener{

    class CarItemViewHolder(var view:ItemListBinding) : RecyclerView.ViewHolder(view.root){

        fun bind(listItem : ListEntity?){
            view.listItem = listItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarItemViewHolder {
       val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemListBinding>(inflater,R.layout.item_list,parent,false)
        return CarItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarItemViewHolder, position: Int) {
       holder.bind(getItem(position))
        holder.view.listener = this
    }




    companion object {
        private val diffUtilCallBack =
        object  : DiffUtil.ItemCallback<ListEntity>(){
            override fun areItemsTheSame(oldItem: ListEntity, newItem: ListEntity): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: ListEntity, newItem: ListEntity): Boolean {
                return oldItem.title == newItem.title && oldItem.id == newItem.id
            }

        }

    }

    override fun listItemClick(v: View) {
        val carId = v.idText.text.toString().toInt()
        val bundle = bundleOf("carId" to carId)



        Navigation.findNavController(v).navigate(R.id.action_listingFragment_to_detailFragment,bundle)

    }

}